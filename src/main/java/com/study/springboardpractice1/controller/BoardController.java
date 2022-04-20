package com.study.springboardpractice1.controller;

import com.study.springboardpractice1.domain.dto.BoardDto;
import com.study.springboardpractice1.domain.dto.MemberDto;
import com.study.springboardpractice1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    HttpServletRequest request;


    // 게시물 목록
    @GetMapping("/board/boardlist")
    public String boardlist() {
        return "board/boardlist";
    }

    // 게시물 쓰기
    @GetMapping("/board/boardwrite")
    public String boardwrite(){
        return "board/boardwrite";
    }

    @PostMapping("/board1/writecontroller")
    @ResponseBody
    public RedirectView boardwritecontroller(BoardDto boardDto) {
        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("logindto");
        boolean result = BoardService.boardwrite(boardDto, memberDto.getMno());
        if (result) {
            return new RedirectView("/board/boardlist");
        } else {
            return new RedirectView("/board/boardwrite");
        }
    }

    // 게시물 수정
    @GetMapping("/board/boardupdate")
    public String boardupdate(){
        return "board/boardupdate";
    }

    // 게시물 상세 보기
    @GetMapping("/board/boardview")
    public String boardview(){
        return "board/boardview";
    }

}
