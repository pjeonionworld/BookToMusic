package com.booktomusic.service;

import com.booktomusic.dto.MemberDto;

public interface MemberService {

	int signUp(MemberDto memberDto);

    MemberDto logIn(MemberDto memberDto);

    boolean isDuplicateId(String memberId);
    
    MemberDto getMemberDetail(String memberId);
    
    MemberDto findMemberInfoByMemberId(String memberId);

    void updateMemberInfo(MemberDto memberDto);
    
    void changePassword(MemberDto memberDto);
}