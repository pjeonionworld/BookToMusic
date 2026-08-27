package com.booktomusic.dto;

public class MusicDto {

    private int musicIdx;
    private String musicTitle;
    private String musicArtist;
    private String musicUrl;
    private String musicImageUrl;

    public MusicDto() {
    }

    public int getMusicIdx() {
        return musicIdx;
    }

    public void setMusicIdx(int musicIdx) {
        this.musicIdx = musicIdx;
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

    public String getMusicImageUrl() {
        return musicImageUrl;
    }

    public void setMusicImageUrl(String musicImageUrl) {
        this.musicImageUrl = musicImageUrl;
    }
}