package com.booktomusic.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MemberDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Size(min = 4, max = 20, message = "아이디는 4~20자입니다.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
        message = "비밀번호는 8자 이상, 영문과 숫자를 포함해야 합니다."
    )
    private String memberPw;

    @NotBlank(message = "이름을 입력해주세요.")
    @Size(max = 10, message = "이름은 10자 이하입니다.")
    private String memberName;

    private LocalDateTime createdAt;

    // member_detail
    private String mbti;
    private String lyricsPreference;

    // 비밀번호 변경
    private String currentPassword;
    private String newPassword;
    private String newPasswordConfirm;

    public MemberDto() {
    }

    public MemberDto(
            String memberId,
            String memberPw,
            String memberName,
            LocalDateTime createdAt,
            String mbti,
            String lyricsPreference,
            String currentPassword,
            String newPassword,
            String newPasswordConfirm
    ) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.createdAt = createdAt;
        this.mbti = mbti;
        this.lyricsPreference = lyricsPreference;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }
}