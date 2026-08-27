package com.booktomusic.dto;

import java.time.LocalDate;

public class RankingDto {

    // 랭킹 공통 정보
    private int rankingIdx;
    private int rankingOrder;
    private long recommendationCount;
    private LocalDate rankingDate;

    // 추천 조합 정보
    private int recommendationIdx;

    // 사용자 성향 필터
    private String mbti;
    private String lyricsPreference;

    // 책 정보
    private int bookIdx;
    private String bookTitle;
    private String bookAuthor;
    private String bookThumbnail;

    // 음악 정보
    private int musicIdx;
    private String musicTitle;
    private String musicArtist;
    private String musicUrl;
    private String musicImageUrl;

    public RankingDto() {
    }

    public int getRankingIdx() {
        return rankingIdx;
    }

    public void setRankingIdx(int rankingIdx) {
        this.rankingIdx = rankingIdx;
    }

    public int getRankingOrder() {
        return rankingOrder;
    }

    public void setRankingOrder(int rankingOrder) {
        this.rankingOrder = rankingOrder;
    }

    public long getRecommendationCount() {
        return recommendationCount;
    }

    public void setRecommendationCount(long recommendationCount) {
        this.recommendationCount = recommendationCount;
    }

    public LocalDate getRankingDate() {
        return rankingDate;
    }

    public void setRankingDate(LocalDate rankingDate) {
        this.rankingDate = rankingDate;
    }

    public int getRecommendationIdx() {
        return recommendationIdx;
    }

    public void setRecommendationIdx(int recommendationIdx) {
        this.recommendationIdx = recommendationIdx;
    }

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public String getLyricsPreference() {
        return lyricsPreference;
    }

    public void setLyricsPreference(String lyricsPreference) {
        this.lyricsPreference = lyricsPreference;
    }

    public int getBookIdx() {
        return bookIdx;
    }

    public void setBookIdx(int bookIdx) {
        this.bookIdx = bookIdx;
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

    public String getBookThumbnail() {
        return bookThumbnail;
    }

    public void setBookThumbnail(String bookThumbnail) {
        this.bookThumbnail = bookThumbnail;
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