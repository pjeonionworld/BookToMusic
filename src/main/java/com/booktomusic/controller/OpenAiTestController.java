package com.booktomusic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booktomusic.dto.MusicDto;
import com.booktomusic.dto.OpenAiResponseDto;
import com.booktomusic.service.OpenAiService;
import com.booktomusic.service.SpotifyService;
import com.booktomusic.service.MusicService;

@RestController
@RequestMapping("/api/openai")
public class OpenAiTestController {

    private final OpenAiService openAiService;
    private final SpotifyService spotifyService;
    private final MusicService musicService;

    public OpenAiTestController(
        OpenAiService openAiService,
        SpotifyService spotifyService,
        MusicService musicService
    ) {
        this.openAiService = openAiService;
        this.spotifyService = spotifyService;
        this.musicService = musicService;
    }

    @GetMapping("/test")
    public MusicDto testRecommendation() {
        OpenAiResponseDto openAiResponse = openAiService.recommendMusic(
            "데미안",
            "헤르만 헤세",
            "소년 싱클레어가 선과 악의 세계 사이에서 방황하며 자신의 내면과 정체성을 찾아 성장하는 이야기",
            "INFP",
            "가사 선호"
        );
        
        MusicDto musicDto = spotifyService.searchMusic(
                openAiResponse.getMusicTitle(),
                openAiResponse.getMusicArtist()
            );

        int musicIdx = musicService.saveMusic(musicDto);

        return musicService.getMusicByIdx(musicIdx);
    }
}