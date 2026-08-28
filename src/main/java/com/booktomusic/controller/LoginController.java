package com.booktomusic.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booktomusic.util.JwtUtil;
import com.booktomusic.dto.MemberDto;
import com.booktomusic.service.MemberService;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    public LoginController(MemberService memberService, JwtUtil jwtUtil) {
        this.memberService = memberService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signUp(
            @RequestBody MemberDto memberDto) {

        Map<String, Object> response = new HashMap<>();

        try {
            int result = memberService.signUp(memberDto);

            if (result > 0) {
                response.put("success", true);
                response.put("message", "회원가입이 완료되었습니다.");

                return ResponseEntity.ok(response);
            }

            response.put("success", false);
            response.put("message", "회원가입에 실패했습니다.");

            return ResponseEntity.badRequest().body(response);

        } catch (IllegalArgumentException e) {

            response.put("success", false);
            response.put("message", e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> logIn(
            @RequestBody MemberDto memberDto) {

        Map<String, Object> response = new HashMap<>();

        MemberDto loginMember = memberService.logIn(memberDto);

        if (loginMember == null) {
            response.put("success", false);
            response.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");

            return ResponseEntity.status(401).body(response);
        }
        
        String accessToken = jwtUtil.createAccessToken(loginMember.getMemberId());

        response.put("success", true);
        response.put("message", "로그인에 성공했습니다.");
        response.put("accessToken", accessToken);
        response.put("member", loginMember);

        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/password")
    public ResponseEntity<?> changePassword(
            @RequestBody MemberDto memberDto) {

        try {
            memberService.changePassword(memberDto);

            return ResponseEntity.ok(
                    Map.of("message", "비밀번호가 변경되었습니다.")
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(
                    Map.of("message", e.getMessage())
            );
        }
    }
}