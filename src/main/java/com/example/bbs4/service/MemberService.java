package com.example.bbs4.service;

import com.example.bbs4.domain.Member;
import com.example.bbs4.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MemberService {
    @Autowired(required = false)
    MemberMapper memberMapper;

    // 회원가입
    public void join(Member member) {
        System.out.println("service join() : " + member.toString());
        memberMapper.join(member);
    }

    // 로그인
    public Member login(Member member) {
        System.out.println("service login() : " + member.toString());
        System.out.println("service login() return : " + memberMapper.login(member));
        return memberMapper.login(member);
    }

    // 회원 리스트
    public ArrayList<Member> memberList() {
        System.out.println("service memberList() : " + memberMapper.memberList());
        return memberMapper.memberList();
    }
}
