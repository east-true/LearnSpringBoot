package com.example.bbs4.service;

import com.example.bbs4.domain.Board;
import com.example.bbs4.domain.Member;
import com.example.bbs4.domain.Reply;
import com.example.bbs4.mapper.BoardMapper;
import com.example.bbs4.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BoardService {
    @Autowired(required = false)
    BoardMapper boardMapper;

    // 글작성
    public void write(Board board) {
        boardMapper.write(board);
    }

    // 글목록
    public ArrayList<Board> boardList(Criteria criteria) {
        System.out.println("service boardList() : " + boardMapper.boardList(criteria));
        return boardMapper.boardList(criteria);
    }

    // 글 세부
    public Board select(long bno) {
        return boardMapper.select(bno);
    }

    // 조회수
    public void count(long bno) {
        boardMapper.count(bno);
    }

    // 추천수
    public void reco(long bno) {
        boardMapper.reco(bno);
    }

    //글 삭제
    public void delete(long bno) {
        boardMapper.delete(bno);
    }

    // 글 수정
    public void update(Board board) {
        boardMapper.update(board);
    }

    // 총 게시글 수
    public int boardListCnt() {
        return boardMapper.boardListCnt();
    }

    // 댓글

    // 댓글 작성
    public void reply(Reply reply) {
        boardMapper.reply(reply);
    }

    // 댓글 리스트
    public ArrayList<Reply> replyList(Criteria criteria) {
        return boardMapper.replyList(criteria);
    }

    // 댓글추천
    public void up(Reply reply) {
        boardMapper.up(reply);
    }
    // 댓글비추
    public void down(Reply reply) {
        boardMapper.down(reply);
    }

    // 총 댓글 수
    public int replyListCnt(long bno) {
        return boardMapper.replyListCnt(bno);
    }
}
