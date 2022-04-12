package com.study.springboardpractice1.domain.dto;

import com.study.springboardpractice1.domain.entity.board.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardDto {

    private int bno;
    private int groupno;
    private String btitle;
    private String bcontents;
    private String bwriter;
    private LocalDateTime bcreateDate;

    public BoardEntity toentity() {
        return BoardEntity.builder()
                .btitle(this.btitle)
                .bcontents(this.bcontents)
                .build();
    }

}
