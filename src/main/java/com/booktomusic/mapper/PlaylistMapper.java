package com.booktomusic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.booktomusic.dto.PlaylistDetailDto;
import com.booktomusic.dto.PlaylistDto;

@Mapper
public interface PlaylistMapper {

    List<PlaylistDto> findPlaylistByMemberId(@Param("memberId") String memberId);

    int insertPlaylist(PlaylistDto playlistDto);

    int insertPlaylistDetail(PlaylistDetailDto playlistDetailDto);

    List<PlaylistDetailDto> findPlaylistDetailByPlaylistIdx(@Param("playlistIdx") int playlistIdx);
    
    int updatePlaylistOpenYn(
            @Param("playlistIdx") int playlistIdx,
            @Param("openYn") int openYn);

    int updatePlaylistName(
            @Param("playlistIdx") int playlistIdx,
            @Param("playlistName") String playlistName);

    int deletePlaylistDetailByPlaylistIdx(@Param("playlistIdx") int playlistIdx);

    int deletePlaylist(@Param("playlistIdx") int playlistIdx);
    
    int deletePlaylistDetail(@Param("detailIdx") int detailIdx);
    
    List<PlaylistDto> findOpenPlaylist(@Param("memberId") String memberId);

    int updatePlaylistDescription(
            @Param("playlistIdx") int playlistIdx,
            @Param("description") String description);

}