package com.example.bbs4.controller;

import com.example.bbs4.domain.Reply;
import com.example.bbs4.service.BoardService;
import com.example.bbs4.service.Criteria;
import com.example.bbs4.service.MemberService;
import com.example.bbs4.service.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class indexController {
    //Autowired를 통해 빈 설정(의존성 주입? 필드 주입)
    @Autowired
    MemberService memberService;
    @Autowired
    BoardService boardService;

    // GetMapping request가 get방식이면 해당 매핑에 관련된 cnotroller내부 메소드 호출
    @GetMapping("/")
    public String index(Model model, Criteria criteria, @RequestParam(defaultValue = "1") int page) // Model을 통해 가져온 게시판 목록을 리턴 페이지에 뿌려줌(리턴타입이 list일 경우 많이 사용)
    {
        Pagination pagination = new Pagination(boardService.boardListCnt(), page, 10);
        criteria.setPage(pagination.getPage());
        model.addAttribute("pagination", pagination);
        model.addAttribute("boardList" ,boardService.boardList(criteria));
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) { // HttpSession에 저장된 정보를 삭제하여 로그아웃
        httpSession.invalidate();
        // 일반적으로 return "원하는 리턴 페이지명"으로 할 시엔 com/board/~~~/리턴 페이지 로 여태 수행한
        // url이 쌓여서 반환되는데 redirect를 사용하면 콜론 뒤에 오는 주소가 ~~.com뒤에 오게됨
        return "redirect:/";
    }

    @GetMapping("/memberList")
    public String memberList(Model model) {
        model.addAttribute("memberList", memberService.memberList());
        return "memberList";
    }

    @GetMapping("/write")
    public String write() {
        return "write";
    }

    @GetMapping("/select/{bno}")
    public String select(Model model, @PathVariable long bno,
                         Criteria criteria, @RequestParam(defaultValue = "1") int page) {
        Pagination pagination = new Pagination(boardService.replyListCnt(bno), page, 5);
        pagination.setPageSize(5);
        criteria.setBno(bno);
        criteria.setPage(pagination.getPage());
        criteria.setPerPageNum(pagination.getPageSize());
        System.out.println("select bno : " + bno);
        boardService.count(bno);
        model.addAttribute("pagination", pagination);
        model.addAttribute("select", boardService.select(bno));
        model.addAttribute("reply", boardService.replyList(criteria));
        return "select";
    }

    //db에서 나오는 정보를 조회해서 나올땐 model사용
    @GetMapping("/update/{bno}")
    public String update(Model model, @PathVariable long bno) {
        model.addAttribute("select", boardService.select(bno));
        return "update";
    }
}
