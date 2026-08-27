package com.booktomusic.dto;

public class OpenAiResponseDto {

    private String musicTitle;
    private String musicArtist;
    private String musicUrl;

    public OpenAiResponseDto() {
    }

    public OpenAiResponseDto(String musicTitle, String musicArtist, String musicUrl) {
        this.musicTitle = musicTitle;
        this.musicArtist = musicArtist;
        this.musicUrl = musicUrl;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }
}