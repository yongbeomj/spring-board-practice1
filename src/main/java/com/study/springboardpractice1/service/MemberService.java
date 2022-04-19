package com.study.springboardpractice1.service;

import com.study.springboardpractice1.domain.dto.MemberDto;
import com.study.springboardpractice1.domain.entity.member.MemberEntity;
import com.study.springboardpractice1.domain.entity.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    HttpServletRequest request;

    // 회원가입
    public boolean signup(MemberDto memberDto) {
        memberRepository.save(memberDto.toentity());
        return true;
    }

    // 로그인
    public boolean login(String mid, String mpw) {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        for (MemberEntity memberEntity : memberEntityList) {
            if (memberEntity.getMid().equals(mid) && memberEntity.getMpw().equals(mpw)) {
                MemberDto memberDto = MemberDto.builder()
                        .mno(memberEntity.getMno())
                        .mid(memberEntity.getMid())
                        .mpw(memberEntity.getMpw())
                        .build();
                HttpSession session = request.getSession();
                session.setAttribute("logindto", memberDto);
                return true;
            }
        }
        return false;
    }

    // mno -> 회원정보 전환
    @Transactional
    public MemberDto getmemberDto(int mno){
        Optional<MemberEntity> entityOptional = memberRepository.findById(mno);
        return MemberDto.builder()
                .mid(entityOptional.get().getMid())
                .mpw(entityOptional.get().getMpw())
                .mname(entityOptional.get().getMname())
                .memail(entityOptional.get().getMemail())
                .mcreateDate(entityOptional.get().getCreatedDate())
                .build();
    }



}
