package com.booktomusic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.booktomusic.dto.MemberDto;

@Mapper
public interface MemberMapper {

	int signUp(MemberDto memberDto);

    MemberDto logIn(String memberId);
    
    int insertMemberDetail(MemberDto memberDto);
    
    MemberDto findMemberDetailById(@Param("memberId") String memberId);
    
    MemberDto findMemberInfoByMemberId(@Param("memberId") String memberId);

    int updateMemberInfo(MemberDto memberDto);

    int updateMemberDetail(MemberDto memberDto);
    
    int updatePassword(@Param("memberId") String memberId, @Param("memberPw") String memberPw);
}