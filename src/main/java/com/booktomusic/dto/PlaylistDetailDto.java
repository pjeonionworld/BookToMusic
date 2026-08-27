package com.booktomusic.dto;

public class PlaylistDetailDto {

    private Integer detailIdx;
    private int playlistIdx;
    private int recommendationIdx;

    private String playlistName;
    private String description;
    private String memberName;

    private String bookTitle;
    private String bookAuthor;

    private String musicTitle;
    private String musicArtist;
    private String musicUrl;

    public PlaylistDetailDto() {
    }

    public PlaylistDetailDto(
            Integer detailIdx,
            int playlistIdx,
            int recommendationIdx,
            String playlistName,
            String description,
            String memberName,
            String bookTitle,
            String bookAuthor,
            String musicTitle,
            String musicArtist,
            String musicUrl) {

        this.detailIdx = detailIdx;
        this.playlistIdx = playlistIdx;
        this.recommendationIdx = recommendationIdx;
        this.playlistName = playlistName;
        this.description = description;
        this.memberName = memberName;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.musicTitle = musicTitle;
        this.musicArtist = musicArtist;
        this.musicUrl = musicUrl;
    }

    public Integer getDetailIdx() {
        return detailIdx;
    }

    public void setDetailIdx(Integer detailIdx) {
        this.detailIdx = detailIdx;
    }

    public int getPlaylistIdx() {
        return playlistIdx;
    }

    public void setPlaylistIdx(int playlistIdx) {
        this.playlistIdx = playlistIdx;
    }

    public int getRecommendationIdx() {
        return recommendationIdx;
    }

    public void setRecommendationIdx(int recommendationIdx) {
        this.recommendationIdx = recommendationIdx;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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
}