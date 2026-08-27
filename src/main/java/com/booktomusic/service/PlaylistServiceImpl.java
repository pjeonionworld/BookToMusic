package com.booktomusic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booktomusic.dto.PlaylistDetailDto;
import com.booktomusic.dto.PlaylistDto;
import com.booktomusic.mapper.PlaylistMapper;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistMapper playlistMapper;

    public PlaylistServiceImpl(PlaylistMapper playlistMapper) {
        this.playlistMapper = playlistMapper;
    }

    @Override
    public List<PlaylistDto> findPlaylistByMemberId(String memberId) {
        return playlistMapper.findPlaylistByMemberId(memberId);
    }

    @Override
    @Transactional
    public int createPlaylistWithDetail(
            PlaylistDto playlistDto,
            int recommendationIdx) {

        int playlistInsertResult =
                playlistMapper.insertPlaylist(playlistDto);

        if (playlistInsertResult != 1) {
            throw new IllegalStateException(
                    "플레이리스트 생성에 실패했습니다."
            );
        }

        PlaylistDetailDto playlistDetailDto =
                new PlaylistDetailDto();

        playlistDetailDto.setPlaylistIdx(
                playlistDto.getPlaylistIdx()
        );

        playlistDetailDto.setRecommendationIdx(
                recommendationIdx
        );

        int playlistDetailInsertResult =
                playlistMapper.insertPlaylistDetail(
                        playlistDetailDto
                );

        if (playlistDetailInsertResult != 1) {
            throw new IllegalStateException(
                    "플레이리스트 상세 저장에 실패했습니다."
            );
        }

        return playlistDetailInsertResult;
    }

    @Override
    public int addPlaylistDetail(
            PlaylistDetailDto playlistDetailDto) {

        int playlistDetailInsertResult =
                playlistMapper.insertPlaylistDetail(
                        playlistDetailDto
                );

        if (playlistDetailInsertResult != 1) {
            throw new IllegalStateException(
                    "플레이리스트 추가에 실패했습니다."
            );
        }

        return playlistDetailInsertResult;
    }

    @Override
    public List<PlaylistDetailDto>
            findPlaylistDetailByPlaylistIdx(
                    int playlistIdx) {

        return playlistMapper
                .findPlaylistDetailByPlaylistIdx(
                        playlistIdx
                );
    }
    
    
    @Override
    public int updatePlaylistOpenYn(int playlistIdx, int openYn) {
        return playlistMapper.updatePlaylistOpenYn(
                playlistIdx,
                openYn
        );
    }

    @Override
    public int updatePlaylistName(
            int playlistIdx,
            String playlistName) {

        return playlistMapper.updatePlaylistName(
                playlistIdx,
                playlistName
        );
    }

    @Override
    @Transactional
    public int deletePlaylist(int playlistIdx) {

        playlistMapper.deletePlaylistDetailByPlaylistIdx(
                playlistIdx
        );

        return playlistMapper.deletePlaylist(
                playlistIdx
        );
    }
    
    @Override
    public int deletePlaylistDetail(int detailIdx) {
        return playlistMapper.deletePlaylistDetail(detailIdx);
    }
    
    @Override
    public List<PlaylistDto> findOpenPlaylist(String memberId) {

        return playlistMapper.findOpenPlaylist(memberId);
    }

    @Override
    public int updatePlaylistDescription(
            int playlistIdx,
            String description) {

        return playlistMapper.updatePlaylistDescription(
                playlistIdx,
                description
        );
    }
}