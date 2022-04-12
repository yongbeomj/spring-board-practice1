package com.study.springboardpractice1.domain.entity.member;

import com.study.springboardpractice1.domain.BaseTimeEntity;
import com.study.springboardpractice1.domain.entity.board.BoardEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"boardEntities"})
@Builder
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {

    // 회원번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mno")
    private int mno;

    // 아이디
    @Column(name = "mid")
    private String mid;

    // 비밀번호
    @Column(name = "mpw")
    private String mpw;

    // 이름
    @Column(name = "mname")
    private String mname;

    // 이메일
    @Column(name = "memail")
    private String memail;

    // 게시판 연결
    @OneToMany(mappedBy = "memberEntity")
    private List<BoardEntity> boardEntities = new ArrayList<>();


}
