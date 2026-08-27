package com.booktomusic.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.booktomusic.dto.OpenAiResponseDto;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/responses";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    @Value("${openai.api.model}")
    private String openAiApiModel;

    public OpenAiServiceImpl(ObjectMapper objectMapper) {
        this.restTemplate = new RestTemplate();
        this.objectMapper = objectMapper;
    }

    @Override
    public OpenAiResponseDto recommendMusic(
        String bookTitle,
        String bookAuthor,
        String bookContents,
        String mbti,
        String lyricsPreference
    ) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openAiApiKey);

            Map<String, Object> requestBody = createRequestBody(
                bookTitle,
                bookAuthor,
                bookContents,
                mbti,
                lyricsPreference
            );

            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                OPENAI_API_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
            );

            return parseResponse(response.getBody());

        } catch (Exception exception) {
            throw new RuntimeException("OpenAI 음악 추천 중 오류가 발생했습니다.", exception);
        }
    }

    private Map<String, Object> createRequestBody(
        String bookTitle,
        String bookAuthor,
        String bookContents,
        String mbti,
        String lyricsPreference
    ) {
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("model", openAiApiModel);
        requestBody.put("input", createPrompt(
            bookTitle,
            bookAuthor,
            bookContents,
            mbti,
            lyricsPreference
        ));
        requestBody.put("text", createResponseFormat());

        return requestBody;
    }

    private String createPrompt(
        String bookTitle,
        String bookAuthor,
        String bookContents,
        String mbti,
        String lyricsPreference
    ) {
        String safeBookTitle = bookTitle != null && !bookTitle.isBlank()
            ? bookTitle
            : "정보 없음";

        String safeBookAuthor = bookAuthor != null && !bookAuthor.isBlank()
            ? bookAuthor
            : "정보 없음";

        String safeBookContents = bookContents != null && !bookContents.isBlank()
            ? bookContents
            : "정보 없음";

        String memberMbti = mbti != null && !mbti.isBlank()
            ? mbti
            : "정보 없음";

        String memberLyricsPreference = lyricsPreference != null && !lyricsPreference.isBlank()
            ? lyricsPreference
            : "정보 없음";

        return """
            너는 독자가 책에 더욱 몰입할 수 있도록 음악을 추천하는 전문 큐레이터다.

        	책의 전체적인 분위기뿐만 아니라
        	주인공의 상황, 감정선, 관계와 갈등, 이야기의 흐름을 함께 분석하여
        	작품의 감정을 가장 잘 경험할 수 있는 실제 존재하는 노래 한 곡을 추천한다.

        	추천 시 다음 기준을 순서대로 고려한다.
        	1. 주인공의 상황과 핵심 감정선
        	2. 책의 전체적인 분위기와 이야기의 흐름
        	3. 사용자의 MBTI
        	4. 사용자의 가사 선호도
        	
        	가사 선호도는 다음과 같이 해석하여 반드시 추천에 반영한다.
        	- LYRICS: 책의 상황과 감정을 표현하는 가사와 메시지를 중요하게 고려한다.
        	- MELODY: 가사보다 멜로디, 리듬, 음색 등 음악 자체의 분위기를 중요하게 고려한다.
        	- BALANCED: 가사와 음악적 분위기를 균형 있게 고려한다.
        	
        	유명하거나 대중적이라는 이유만으로 곡을 선택하지 않는다.
        	특히 여러 책에 쉽게 적용할 수 있는 전형적인 위로곡이나 감성곡을 반복적으로 추천하지 말고,
        	해당 작품만의 상황과 감정에 구체적으로 어울리는 곡을 선택한다.

        	추천곡은 반드시 Spotify에서 검색 가능한 공식 음원이어야 하며,
        	노래 제목과 아티스트명을 정확하게 반환한다.

        	저자의 국적과 작품의 문화권도 고려하되,
        	국적보다 책과 음악의 실제 어울림을 우선한다.

        	[책 정보]
        	책 제목: %s
        	저자: %s
        	책 소개:
        	%s

        	[사용자 정보]
        	MBTI: %s
        	가사 선호도: %s

        	반드시 아래 JSON 형식만 반환한다.

        	{
        	"musicTitle": "노래 제목",
        	"musicArtist": "가수"
        	}
            """.formatted(
                safeBookTitle,
                safeBookAuthor,
                safeBookContents,
                memberMbti,
                memberLyricsPreference
            );
    }

    private Map<String, Object> createResponseFormat() {
        Map<String, Object> musicTitleSchema = new HashMap<>();
        musicTitleSchema.put("type", "string");

        Map<String, Object> musicArtistSchema = new HashMap<>();
        musicArtistSchema.put("type", "string");

        Map<String, Object> properties = new HashMap<>();
        properties.put("musicTitle", musicTitleSchema);
        properties.put("musicArtist", musicArtistSchema);

        Map<String, Object> schema = new HashMap<>();
        schema.put("type", "object");
        schema.put("properties", properties);
        schema.put("required", List.of("musicTitle", "musicArtist"));
        schema.put("additionalProperties", false);

        Map<String, Object> format = new HashMap<>();
        format.put("type", "json_schema");
        format.put("name", "music_recommendation");
        format.put("strict", true);
        format.put("schema", schema);

        Map<String, Object> text = new HashMap<>();
        text.put("format", format);

        return text;
    }

    private OpenAiResponseDto parseResponse(String responseBody) throws Exception {
        if (responseBody == null || responseBody.isBlank()) {
            throw new RuntimeException("OpenAI 응답이 비어 있습니다.");
        }

        JsonNode rootNode = objectMapper.readTree(responseBody);

        for (JsonNode outputItem : rootNode.path("output")) {
            for (JsonNode contentItem : outputItem.path("content")) {
                if ("output_text".equals(contentItem.path("type").asText())) {
                    String outputText = contentItem.path("text").asText();

                    OpenAiResponseDto responseDto =
                        objectMapper.readValue(outputText, OpenAiResponseDto.class);

                    responseDto.setMusicUrl(null);

                    return responseDto;
                }
            }
        }

        throw new RuntimeException("OpenAI 응답에서 추천곡 정보를 찾을 수 없습니다.");
    }
}