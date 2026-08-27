package com.booktomusic.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.booktomusic.dto.MusicDto;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class SpotifyServiceImpl implements SpotifyService {

    private static final String SPOTIFY_TOKEN_URL =
        "https://accounts.spotify.com/api/token";

    private static final String SPOTIFY_SEARCH_URL =
        "https://api.spotify.com/v1/search";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${spotify.client.id}")
    private String spotifyClientId;

    @Value("${spotify.client.secret}")
    private String spotifyClientSecret;

    public SpotifyServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public MusicDto searchMusic(String musicTitle, String musicArtist) {
        try {
            String accessToken = getSpotifyAccessToken();

            URI searchUri = createSpotifySearchUri(
                    musicTitle,
                    musicArtist
            );

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);

            HttpEntity<Void> requestEntity =
                    new HttpEntity<>(headers);

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            searchUri,
                            HttpMethod.GET,
                            requestEntity,
                            String.class
                    );

            return parseSpotifySearchResponse(
                    response.getBody(),
                    musicTitle,
                    musicArtist
            );

        } catch (Exception exception) {
            throw new RuntimeException(
                    "Spotify 음악 검색 중 오류가 발생했습니다.",
                    exception
            );
        }
    }

    private String getSpotifyAccessToken() throws Exception {
        String clientCredentials =
            spotifyClientId + ":" + spotifyClientSecret;

        String encodedCredentials = Base64.getEncoder().encodeToString(
            clientCredentials.getBytes(StandardCharsets.UTF_8)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(
            HttpHeaders.AUTHORIZATION,
            "Basic " + encodedCredentials
        );

        MultiValueMap<String, String> requestBody =
            new LinkedMultiValueMap<>();

        requestBody.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> requestEntity =
            new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            SPOTIFY_TOKEN_URL,
            HttpMethod.POST,
            requestEntity,
            String.class
        );

        String responseBody = response.getBody();

        if (responseBody == null || responseBody.isBlank()) {
            throw new RuntimeException(
                "Spotify 토큰 응답이 비어 있습니다."
            );
        }

        JsonNode rootNode = objectMapper.readTree(responseBody);
        String accessToken =
            rootNode.path("access_token").asString();

        if (accessToken == null || accessToken.isBlank()) {
            throw new RuntimeException(
                "Spotify 액세스 토큰을 발급받지 못했습니다."
            );
        }

        return accessToken;
    }

    private URI createSpotifySearchUri(
            String musicTitle,
            String musicArtist
    ) {
        String searchQuery =
                "track:" + musicTitle
                + " artist:" + musicArtist;

        return UriComponentsBuilder
                .fromUriString(SPOTIFY_SEARCH_URL)
                .queryParam("q", searchQuery)
                .queryParam("type", "track")
                .queryParam("limit", 10)
                .queryParam("market", "KR")
                .encode()
                .build()
                .toUri();
    }

    private MusicDto parseSpotifySearchResponse(
            String responseBody,
            String requestedMusicTitle,
            String requestedMusicArtist
    ) throws Exception {

        if (responseBody == null || responseBody.isBlank()) {
            throw new RuntimeException(
                    "Spotify 검색 응답이 비어 있습니다."
            );
        }

        JsonNode rootNode = objectMapper.readTree(responseBody);

        JsonNode itemsNode = rootNode.path("tracks").path("items");

        if (!itemsNode.isArray() || itemsNode.size() == 0) {
            throw new RuntimeException( "Spotify에서 일치하는 노래를 찾지 못했습니다." );
        }

        JsonNode trackNode = itemsNode.get(0);

        MusicDto musicDto = new MusicDto();

        musicDto.setMusicTitle(requestedMusicTitle);
        musicDto.setMusicArtist(requestedMusicArtist);

        musicDto.setMusicUrl(
                trackNode
                        .path("external_urls")
                        .path("spotify")
                        .asString()
        );

        musicDto.setMusicImageUrl(
                getAlbumImageUrl(
                        trackNode
                                .path("album")
                                .path("images")
                )
        );

        return musicDto;
    }

    private String getAlbumImageUrl(JsonNode imagesNode) {
        if (!imagesNode.isArray() || imagesNode.size() == 0) {
            return null;
        }

        return imagesNode.get(0).path("url").asString();
    }
    
}