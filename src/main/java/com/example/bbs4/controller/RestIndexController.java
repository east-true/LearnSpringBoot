package com.example.bbs4.controller;

import com.example.bbs4.domain.Board;
import com.example.bbs4.domain.Email;
import com.example.bbs4.domain.Member;
import com.example.bbs4.domain.Reply;
import com.example.bbs4.service.BoardService;
import com.example.bbs4.service.EmailService;
import com.example.bbs4.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

// 내부적으로 실행하는것
// 데이터만 사용하는거(화면 보여주는거 말고)
@RestController
public class RestIndexController {
    @Autowired
    MemberService memberService;
    @Autowired
    BoardService boardService;
    @Autowired
    EmailService emailService;

    // PostMapping은 ()안의 url에 요청이 post방식으로 오면 해당 메소드 수행
    // println으로 로그관리(실제론 println보단 log를 빼내어서 보는 방식으로 수행하게됨)
    // 회원가입
    @PostMapping("/rest/join")
    public void join(Member member) {
        System.out.println("restController join(): " + member.toString());
        memberService.join(member);
    }

    // 로그인
    @PostMapping("/rest/login")
    public Member login(Member member, HttpSession httpSession) { // HttpSession에 로그인 정보값을 넣어줘 보관
        System.out.println("restController login() : " + member.toString());
        System.out.println("restController login() return : " + memberService.login(member));
        httpSession.setAttribute("user", memberService.login(member)); // setAttribute(key, value)
        return memberService.login(member);
    }

    // 글작성
    @PostMapping("/rest/write")
    public void write(Board board) {
        System.out.println("restController write() : " + board.toString());
        boardService.write(board);
    }

    // 추천
    @PutMapping("/rest/reco")
    public void reco(long bno) {
        System.out.println("restController reco() : " + bno);
        boardService.reco(bno);
    }

    // 글삭제
    @DeleteMapping("/rest/delete")
    public void delete(long bno) {
        boardService.delete(bno);
    }

    // 글 수정
    @PutMapping("/rest/update")
    public void update(Board board) {
        boardService.update(board);
    }

    // 댓글
    @PostMapping("/rest/reply")
    public void reply(Reply reply) {
        boardService.reply(reply);
    }

    // 댓글 추천
    @PutMapping("/rest/up")
    public void up(Reply reply) {
        boardService.up(reply);
    }

    // 댓글 비추
    @PutMapping("/rest/down")
    public void down(Reply reply) {
        boardService.down(reply);
    }

    // 이메일

    // 메일 전송
    @PostMapping("/rest/email")
    public void sendMail(Email email) throws Exception { // 이메일시 무조건 exception처리
        emailService.sendSimpleMessage(email.getUserEmail());
    }

    // 인증 코드
    @PostMapping("/rest/confirm")
    public int confirm(Email email) {
        if(EmailService.ePw.equals(email.getConfirm())){
            return EmailService.CONFIRM;
        }else {
            return EmailService.REJECT;
        }
    }
}
