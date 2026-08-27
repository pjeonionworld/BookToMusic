package com.booktomusic.dto;

public class PlaylistDto {

    private Integer playlistIdx;
    private String memberId;
    private String playlistName;
    private int openYn;
    private Integer musicCount;
    private String description;

    private String memberName;
    private String mbti;

    public PlaylistDto() {
    }

    public PlaylistDto(
            Integer playlistIdx,
            String memberId,
            String playlistName,
            int openYn,
            Integer musicCount,
            String description,
            String memberName,
            String mbti) {

        this.playlistIdx = playlistIdx;
        this.memberId = memberId;
        this.playlistName = playlistName;
        this.openYn = openYn;
        this.musicCount = musicCount;
        this.description = description;
        this.memberName = memberName;
        this.mbti = mbti;
    }

    public Integer getPlaylistIdx() {
        return playlistIdx;
    }

    public void setPlaylistIdx(Integer playlistIdx) {
        this.playlistIdx = playlistIdx;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getOpenYn() {
        return openYn;
    }

    public void setOpenYn(int openYn) {
        this.openYn = openYn;
    }

    public Integer getMusicCount() {
        return musicCount;
    }

    public void setMusicCount(Integer musicCount) {
        this.musicCount = musicCount;
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

    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }
}