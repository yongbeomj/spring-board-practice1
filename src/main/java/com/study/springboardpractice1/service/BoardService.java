package com.study.springboardpractice1.service;

import com.study.springboardpractice1.domain.dto.BoardDto;
import com.study.springboardpractice1.domain.entity.board.BoardEntity;
import com.study.springboardpractice1.domain.entity.board.BoardRepository;
import com.study.springboardpractice1.domain.entity.member.MemberEntity;
import com.study.springboardpractice1.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    // boardwrite
    @Transactional
    public boolean boardwrite(BoardDto boardDto, int mno) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(mno);
        BoardEntity boardEntity = BoardEntity.builder()
                .btitle(boardDto.getBtitle())
                .bcontents(boardDto.getBcontents())
                .memberEntity(memberEntity.get())
                .build();
        boardRepository.save(boardEntity);
        return true;
    }

    // boardlist
    @Transactional
    public ArrayList<BoardDto> boardlist() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        ArrayList<BoardDto> boardDtos = new ArrayList<>();
        for (int i = 0; i < boardEntities.size(); i++) {
            BoardDto boardDto = new BoardDto(
                    boardEntities.get(i).getBno(),
                    boardEntities.get(i).getGroupno(),
                    boardEntities.get(i).getBtitle(),
                    boardEntities.get(i).getBcontents(),
                    boardEntities.get(i).getMemberEntity().getMid(),
                    boardEntities.get(i).getCreatedDate());
            boardDtos.add(boardDto);
        }
        return boardDtos;
    }

    // boardview
    @Transactional
    public BoardDto getboard(int bno) {
        // 게시물을 찾는다
        Optional<BoardEntity> entityOptional = boardRepository.findById(bno);
        int mno = entityOptional.get().getMemberEntity().getMno();
        Optional<MemberEntity> memberEntity = memberRepository.findById(mno);

        // dto에 값을 넣고 리턴한다
        return BoardDto.builder()
                .bno(entityOptional.get().getBno())
                .btitle(entityOptional.get().getBtitle())
                .bcontents(entityOptional.get().getBcontents())
                .bwriter(memberEntity.get().getMid())
                .bcreateDate(entityOptional.get().getCreatedDate())
                .build();
    }

    // boarddelete
    @Transactional
    public boolean boarddelete(int bno) {
        Optional<BoardEntity> entityOptional = boardRepository.findById(bno);
        if (entityOptional.get() != null) {
            boardRepository.delete(entityOptional.get());
            return true;
        } else {
            return false;
        }
    }

    // boardupdate
    @Transactional
    public boolean boardupdate(BoardDto boardDto) {
        try {
            Optional<BoardEntity> entityOptional = boardRepository.findById(boardDto.getBno());
            entityOptional.get().setBtitle(boardDto.getBtitle());
            entityOptional.get().setBcontents(boardDto.getBcontents());
            return true;
        } catch (Exception e) {
            System.out.println("에러");
            System.out.println(e);
            return false;
        }
    }

}
