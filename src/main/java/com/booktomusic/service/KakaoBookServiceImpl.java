package com.booktomusic.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.booktomusic.dto.KakaoBookResponseDto;
import com.booktomusic.service.KakaoBookService;

@Service
public class KakaoBookServiceImpl
        implements KakaoBookService {

    private final RestTemplate restTemplate;
    private final String kakaoApiKey;

    public KakaoBookServiceImpl(
            RestTemplate restTemplate,
            @Value("${kakao.api.key}")
            String kakaoApiKey) {

        this.restTemplate = restTemplate;
        this.kakaoApiKey = kakaoApiKey;
    }

    @Override
    public KakaoBookResponseDto searchBook(
            String query) {

        URI uri = UriComponentsBuilder
                .fromUriString(
                        "https://dapi.kakao.com/v3/search/book"
                )
                .queryParam("query", query)
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();

        headers.set(
                "Authorization",
                "KakaoAK " + kakaoApiKey
        );

        HttpEntity<Void> request =
                new HttpEntity<>(headers);

        ResponseEntity<KakaoBookResponseDto> response =
                restTemplate.exchange(
                        uri,
                        HttpMethod.GET,
                        request,
                        KakaoBookResponseDto.class
                );

        return response.getBody();
    }
}