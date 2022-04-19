package com.study.springboardpractice1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

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
