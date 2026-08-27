package com.booktomusic.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.booktomusic.dto.MemberDto;
import com.booktomusic.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public int signUp(MemberDto memberDto) {

    	if (isDuplicateId(memberDto.getMemberId())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        String encodedPassword =
                passwordEncoder.encode(memberDto.getMemberPw());

        memberDto.setMemberPw(encodedPassword);

        int memberResult = memberMapper.signUp(memberDto);

        if (memberResult != 1) {
            throw new IllegalStateException("회원 저장에 실패했습니다.");
        }

        int memberDetailResult =
                memberMapper.insertMemberDetail(memberDto);

        if (memberDetailResult != 1) {
            throw new IllegalStateException("회원 상세정보 저장에 실패했습니다.");
        }

        return memberResult;
    }

    @Override
    public MemberDto logIn(MemberDto memberDto) {

        MemberDto savedMember =
                memberMapper.logIn(memberDto.getMemberId());

        if (savedMember == null) {
            return null;
        }

        boolean passwordMatches = passwordEncoder.matches(
                memberDto.getMemberPw(),
                savedMember.getMemberPw()
        );

        if (!passwordMatches) {
            return null;
        }

        // 비밀번호가 외부로 반환되지 않도록 제거
        savedMember.setMemberPw(null);

        return savedMember;
    }

    @Override
    public boolean isDuplicateId(String memberId) {

        MemberDto member =
                memberMapper.logIn(memberId);

        return member != null;
    }
    
    @Override
    public MemberDto getMemberDetail(String memberId) {
        return memberMapper.findMemberDetailById(memberId);
    }
    
    //회원정보 변경
    @Override
    public MemberDto findMemberInfoByMemberId(String memberId) {
        return memberMapper.findMemberInfoByMemberId(memberId);
    }

    @Transactional
    @Override
    public void updateMemberInfo(MemberDto memberDto) {
        memberMapper.updateMemberInfo(memberDto);
        memberMapper.updateMemberDetail(memberDto);
    }
    
    
    //비밀번호 변경
    @Override
    public void changePassword(MemberDto memberDto) {

        MemberDto member =
                memberMapper.logIn(memberDto.getMemberId());

        if (member == null) {
            throw new IllegalArgumentException("회원정보를 찾을 수 없습니다.");
        }

        if (!passwordEncoder.matches(
                memberDto.getCurrentPassword(),
                member.getMemberPw())) {

            throw new IllegalArgumentException(
                    "현재 비밀번호가 일치하지 않습니다."
            );
        }

        if (memberDto.getNewPassword() == null ||
                memberDto.getNewPassword().isBlank()) {

            throw new IllegalArgumentException(
                    "변경할 비밀번호를 입력해주세요."
            );
        }

        if (!memberDto.getNewPassword()
                .equals(memberDto.getNewPasswordConfirm())) {

            throw new IllegalArgumentException(
                    "변경할 비밀번호가 일치하지 않습니다."
            );
        }

        String pwRegex =
                "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

        if (!memberDto.getNewPassword().matches(pwRegex)) {
            throw new IllegalArgumentException(
                    "비밀번호는 영문과 숫자를 포함한 8자 이상이어야 합니다."
            );
        }

        String encodedPassword =
                passwordEncoder.encode(memberDto.getNewPassword());

        memberMapper.updatePassword(
                memberDto.getMemberId(),
                encodedPassword
        );
    }
}