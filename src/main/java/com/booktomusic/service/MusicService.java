package com.booktomusic.service;

import com.booktomusic.dto.MusicDto;

public interface MusicService {

    MusicDto getMusicByIdx(int musicIdx);
    
    int saveMusic(MusicDto musicDto);
}