package com.example.bbs4.mapper;

import com.example.bbs4.domain.Member;

import java.util.ArrayList;

// Mapper interface 에서 insert는 void
// 로직 연결 순서 Mapper.xml -> Mapper.interface -> Service -> controller
public interface MemberMapper {
    void join(Member member); // 회원가입
    Member login(Member member); // 로그인
    ArrayList<Member> memberList(); // 회원리스트
}
