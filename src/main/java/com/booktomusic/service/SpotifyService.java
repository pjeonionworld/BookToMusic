package com.booktomusic.service;

import com.booktomusic.dto.MusicDto;

public interface SpotifyService {

    MusicDto searchMusic(String musicTitle, String musicArtist);
}