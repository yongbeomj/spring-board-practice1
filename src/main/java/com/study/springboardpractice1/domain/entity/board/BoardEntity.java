package com.study.springboardpractice1.domain.entity.board;

import com.study.springboardpractice1.domain.BaseTimeEntity;
import com.study.springboardpractice1.domain.entity.member.MemberEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardEntity extends BaseTimeEntity {

    // 게시판번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autokey
    @Column(name = "bno")
    private int bno;

    // 그룹 no
    @Column(name="groupno")
    private int groupno;

    // 제목
    @Column(name = "btitle")
    private String btitle;

    // 내용
    @Column(name = "bcontents")
    private String bcontents;

    // 작성자 = mno
    @ManyToOne
    @JoinColumn(name = "mno")
    private MemberEntity memberEntity;

}
