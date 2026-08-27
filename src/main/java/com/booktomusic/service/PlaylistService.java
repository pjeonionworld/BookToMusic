package com.booktomusic.service;

import java.util.List;

import com.booktomusic.dto.PlaylistDetailDto;
import com.booktomusic.dto.PlaylistDto;

public interface PlaylistService {

    List<PlaylistDto> findPlaylistByMemberId(String memberId);

    int createPlaylistWithDetail(PlaylistDto playlistDto,int recommendationIdx);

    int addPlaylistDetail(PlaylistDetailDto playlistDetailDto);

    List<PlaylistDetailDto> findPlaylistDetailByPlaylistIdx(int playlistIdx);
    
    int updatePlaylistOpenYn(int playlistIdx, int openYn);

    int updatePlaylistName(int playlistIdx, String playlistName);

    int deletePlaylist(int playlistIdx);
    
    int deletePlaylistDetail(int detailIdx);
    
    List<PlaylistDto> findOpenPlaylist(String memberId);

    int updatePlaylistDescription(int playlistIdx,String description);
    
}