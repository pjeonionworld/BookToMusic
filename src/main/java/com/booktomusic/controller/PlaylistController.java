package com.booktomusic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.booktomusic.dto.PlaylistDetailDto;
import com.booktomusic.dto.PlaylistDto;
import com.booktomusic.service.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public List<PlaylistDto> findPlaylistByMemberId(
            @RequestParam("memberId") String memberId) {

        return playlistService.findPlaylistByMemberId(memberId);
    }

    @PostMapping
    public int createPlaylistWithDetail(
            @RequestBody PlaylistDto playlistDto,
            @RequestParam("recommendationIdx") int recommendationIdx) {

        return playlistService.createPlaylistWithDetail(
                playlistDto,
                recommendationIdx
        );
    }

    @PostMapping("/detail")
    public int addPlaylistDetail(
            @RequestBody PlaylistDetailDto playlistDetailDto) {

        return playlistService.addPlaylistDetail(playlistDetailDto);
    }

    @GetMapping("/{playlistIdx}/detail")
    public List<PlaylistDetailDto> findPlaylistDetailByPlaylistIdx(
            @PathVariable("playlistIdx") int playlistIdx) {

        return playlistService.findPlaylistDetailByPlaylistIdx(playlistIdx);
    }
    
    
    @PutMapping("/{playlistIdx}/open")
    public int updatePlaylistOpenYn(
            @PathVariable("playlistIdx") int playlistIdx,
            @RequestParam("openYn") int openYn) {

        return playlistService.updatePlaylistOpenYn(
                playlistIdx,
                openYn
        );
    }

    @PutMapping("/{playlistIdx}/name")
    public int updatePlaylistName(
            @PathVariable("playlistIdx") int playlistIdx,
            @RequestParam("playlistName") String playlistName) {

        return playlistService.updatePlaylistName(
                playlistIdx,
                playlistName
        );
    }

    @DeleteMapping("/{playlistIdx}")
    public int deletePlaylist(
            @PathVariable("playlistIdx") int playlistIdx) {

        return playlistService.deletePlaylist(
                playlistIdx
        );
    }
    
    @DeleteMapping("/detail/{detailIdx}")
    public int deletePlaylistDetail(
            @PathVariable("detailIdx") int detailIdx) {

        return playlistService.deletePlaylistDetail(detailIdx);
    }
    
    @GetMapping("/lounge")
    public List<PlaylistDto> findOpenPlaylist(
            @RequestParam(value = "memberId", required = false) String memberId) {

        return playlistService.findOpenPlaylist(memberId);
    }

    @PutMapping("/{playlistIdx}/description")
    public int updatePlaylistDescription(
            @PathVariable("playlistIdx") int playlistIdx,
            @RequestParam("description") String description) {

        return playlistService.updatePlaylistDescription(
                playlistIdx,
                description
        );
    }
}