package com.booktomusic.service;

import org.springframework.stereotype.Service;

import com.booktomusic.dto.MusicDto;
import com.booktomusic.mapper.MusicMapper;
import com.booktomusic.service.MusicService;

@Service
public class MusicServiceImpl implements MusicService {

    private final MusicMapper musicMapper;

    public MusicServiceImpl(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }

    @Override
    public MusicDto getMusicByIdx(int musicIdx) {
        return musicMapper.selectMusicByIdx(musicIdx);
    }
    
    @Override
    public int saveMusic(MusicDto musicDto) {

        MusicDto existMusic = musicMapper.selectMusicByTitleAndArtist(
            musicDto.getMusicTitle(),
            musicDto.getMusicArtist()
        );

        if (existMusic != null) {
            return existMusic.getMusicIdx();
        }

        musicMapper.insertMusic(musicDto);

        return musicDto.getMusicIdx();
    }
}