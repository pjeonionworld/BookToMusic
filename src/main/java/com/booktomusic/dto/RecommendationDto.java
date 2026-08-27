package com.booktomusic.dto;

import java.sql.Timestamp;

public class RecommendationDto {

    private int recommendationIdx;
    private String memberId;
    private int bookIdx;
    private int musicIdx;
    private Timestamp createdAt;

    // BOOK
    private String bookTitle;
    private String bookAuthor;

    // MUSIC
    private String musicTitle;
    private String musicArtist;
    private String musicUrl;
    private String musicImageUrl;

    public RecommendationDto() {
    }

    public RecommendationDto(
            int recommendationIdx,
            String memberId,
            int bookIdx,
            int musicIdx,
            Timestamp createdAt,
            String bookTitle,
            String bookAuthor,
            String musicTitle,
            String musicArtist,
            String musicUrl,
            String musicImageUrl) {

        this.recommendationIdx = recommendationIdx;
        this.memberId = memberId;
        this.bookIdx = bookIdx;
        this.musicIdx = musicIdx;
        this.createdAt = createdAt;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.musicTitle = musicTitle;
        this.musicArtist = musicArtist;
        this.musicUrl = musicUrl;
        this.musicImageUrl = musicImageUrl;
    }

    public int getRecommendationIdx() {
        return recommendationIdx;
    }

    public void setRecommendationIdx(int recommendationIdx) {
        this.recommendationIdx = recommendationIdx;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getBookIdx() {
        return bookIdx;
    }

    public void setBookIdx(int bookIdx) {
        this.bookIdx = bookIdx;
    }

    public int getMusicIdx() {
        return musicIdx;
    }

    public void setMusicIdx(int musicIdx) {
        this.musicIdx = musicIdx;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
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