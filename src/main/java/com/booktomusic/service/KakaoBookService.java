package com.booktomusic.service;

import com.booktomusic.dto.KakaoBookResponseDto;

public interface KakaoBookService {

    KakaoBookResponseDto searchBook(
            String query
    );
}