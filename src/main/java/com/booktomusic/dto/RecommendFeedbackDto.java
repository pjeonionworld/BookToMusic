package com.booktomusic.dto;

import java.sql.Timestamp;

public class RecommendFeedbackDto {

    private Integer recommendFeedbackIdx;
    private String memberId;
    private int recommendationIdx;
    private int likeYn;
    private int dislikeYn;
    private Timestamp createdAt;

    // BOOK 조인 결과
    private Integer bookIdx;
    private String bookTitle;
    private String bookAuthor;

    // MUSIC 조인 결과
    private Integer musicIdx;
    private String musicTitle;
    private String musicArtist;
    private String musicUrl;

    public RecommendFeedbackDto() {
    }

    public RecommendFeedbackDto(
            Integer recommendFeedbackIdx,
            String memberId,
            int recommendationIdx,
            int likeYn,
            int dislikeYn,
            Timestamp createdAt,
            Integer bookIdx,
            String bookTitle,
            String bookAuthor,
            Integer musicIdx,
            String musicTitle,
            String musicArtist,
            String musicUrl) {

        this.recommendFeedbackIdx = recommendFeedbackIdx;
        this.memberId = memberId;
        this.recommendationIdx = recommendationIdx;
        this.likeYn = likeYn;
        this.dislikeYn = dislikeYn;
        this.createdAt = createdAt;
        this.bookIdx = bookIdx;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.musicIdx = musicIdx;
        this.musicTitle = musicTitle;
        this.musicArtist = musicArtist;
        this.musicUrl = musicUrl;
    }

    public Integer getRecommendFeedbackIdx() {
        return recommendFeedbackIdx;
    }

    public void setRecommendFeedbackIdx(Integer recommendFeedbackIdx) {
        this.recommendFeedbackIdx = recommendFeedbackIdx;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getRecommendationIdx() {
        return recommendationIdx;
    }

    public void setRecommendationIdx(int recommendationIdx) {
        this.recommendationIdx = recommendationIdx;
    }

    public int getLikeYn() {
        return likeYn;
    }

    public void setLikeYn(int likeYn) {
        this.likeYn = likeYn;
    }

    public int getDislikeYn() {
        return dislikeYn;
    }

    public void setDislikeYn(int dislikeYn) {
        this.dislikeYn = dislikeYn;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getBookIdx() {
        return bookIdx;
    }

    public void setBookIdx(Integer bookIdx) {
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

    public Integer getMusicIdx() {
        return musicIdx;
    }

    public void setMusicIdx(Integer musicIdx) {
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
}