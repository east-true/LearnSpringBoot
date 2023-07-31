package com.example.bbs4.mapper;

import com.example.bbs4.domain.Board;
import com.example.bbs4.domain.Member;
import com.example.bbs4.domain.Reply;
import com.example.bbs4.service.Criteria;
import org.springframework.ui.Model;

import java.util.ArrayList;

public interface BoardMapper {
    void write(Board board); // 글작성

    ArrayList<Board> boardList(Criteria criteria); // 글목록

    Board select(long bno); // 글열람

    void count(long bno); // 조회수

    void reco(long bno); //추천수

    void delete(long bno); // 글 삭제

    void update(Board board); // 글 수정

    int boardListCnt(); // 총 게시글 수

    //reply
    void reply(Reply reply); // 댓글 등록

    ArrayList<Reply> replyList(Criteria criteria); // 댓글 목록

    void up(Reply reply); // 댓글 추천
    void down(Reply reply); // 댓글 비추

    int replyListCnt(long bno); // 총 댓글 수
}
