package com.study.springboardpractice1.domain.dto;

import com.study.springboardpractice1.domain.entity.member.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberDto {

    private int mno;
    private String mid;
    private String mpw;
    private String mname;
    private String memail;
    private LocalDateTime mcreateDate;

    public MemberEntity toentity() {
        return MemberEntity.builder()
                .mid(this.mid)
                .mpw(this.mpw)
                .mname(this.mname)
                .memail(this.memail)
                .build();
    }

}
