package com.study.springboardpractice1.service;

import com.study.springboardpractice1.domain.dto.BoardDto;
import com.study.springboardpractice1.domain.entity.board.BoardEntity;
import com.study.springboardpractice1.domain.entity.board.BoardRepository;
import com.study.springboardpractice1.domain.entity.member.MemberEntity;
import com.study.springboardpractice1.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
