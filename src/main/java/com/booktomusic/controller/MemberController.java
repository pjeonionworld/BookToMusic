package com.booktomusic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booktomusic.dto.MemberDto;
import com.booktomusic.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // 회원정보 조회
    @GetMapping("/info")
    public MemberDto getMemberInfo(
            @RequestParam("memberId") String memberId) {

        return memberService.findMemberInfoByMemberId(memberId);
    }


    // 회원정보 수정
    @PutMapping("/info")
    public void updateMemberInfo(
            @RequestBody MemberDto memberDto) {

        memberService.updateMemberInfo(memberDto);
    }
}