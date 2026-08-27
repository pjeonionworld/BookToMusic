package com.booktomusic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.booktomusic.dto.MusicDto;
import com.booktomusic.service.MusicService;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/{musicIdx}")
    public ResponseEntity<MusicDto> getMusicByIdx(
    		@PathVariable("musicIdx") int musicIdx) {

        MusicDto music = musicService.getMusicByIdx(musicIdx);

        if (music == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(music);
    }
}