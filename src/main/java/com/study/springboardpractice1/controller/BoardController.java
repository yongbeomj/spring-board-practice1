package com.study.springboardpractice1.controller;

import com.study.springboardpractice1.domain.dto.BoardDto;
import com.study.springboardpractice1.domain.dto.MemberDto;
import com.study.springboardpractice1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    HttpServletRequest request;


    // 게시물 목록
    @GetMapping("/board/boardlist")
    public String boardlist(Model model) {
        ArrayList<BoardDto> boardDtos = boardService.boardlist();
        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("logindto");
        model.addAttribute("memberDto", memberDto);
        model.addAttribute("boardDtos", boardDtos);
        return "board/boardlist";
    }

    // 게시물 쓰기
    @GetMapping("/board/boardwrite")
    public String boardwrite(){
        return "board/boardwrite";
    }

    @PostMapping("/board/writecontroller")
    @ResponseBody
    public RedirectView boardwritecontroller(BoardDto boardDto) {
        HttpSession session = request.getSession();
        MemberDto memberDto = (MemberDto) session.getAttribute("logindto");
        boolean result = boardService.boardwrite(boardDto, memberDto.getMno());
        if (result) {
            return new RedirectView("/board/boardlist");
        } else {
            return new RedirectView("/board/boardwrite");
        }
    }

    // 게시물 상세 보기
    @GetMapping("/board/boardview/{bno}")
    public String boardview(@PathVariable("bno") int bno, Model model) {
        BoardDto boardDto = boardService.getboard(bno);
        model.addAttribute("boardDto", boardDto);
        return "board/boardview";
    }

    // 게시물 수정
    @GetMapping("/board/boardupdate/{bno}")
    public String boardupdate(@PathVariable("bno") int bno, Model model){
        BoardDto boardDto = boardService.getboard(bno);
        model.addAttribute("boardDto", boardDto);
        return "board/boardupdate";
    }

    @PostMapping("/board/updatecontroller")
    public String updatecontroller3(@RequestParam("bno") int bno,
                                    @RequestParam("btitle") String btitle,
                                    @RequestParam("bcontents") String bcontents) {
        try {
            BoardDto boardDto = BoardDto.builder()
                    .bno(bno)
                    .btitle(btitle)
                    .bcontents(bcontents)
                    .build();
            boardService.boardupdate(boardDto);
        } catch (Exception e) {
            System.out.println("에러");
            System.out.println(e);
        }
        return "redirect:/board/boardview/" + bno;
    }

    // 게시물 삭제
    @GetMapping("/board/boarddelete")
    @ResponseBody
    public int boarddelete(@RequestParam("bno") int bno) {
        boolean result = boardService.boarddelete(bno);
        if (result) {
            return 1;
        } else {
            return 2;
        }
    }

}
