package com.booktomusic.dto;

import java.util.List;

public class KakaoBookResponseDto {

    private List<KakaoBookDto> documents;

    public KakaoBookResponseDto() {
    }

    public List<KakaoBookDto> getDocuments() {
        return documents;
    }

    public void setDocuments(List<KakaoBookDto> documents) {
        this.documents = documents;
    }
}