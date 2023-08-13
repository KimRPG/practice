package com.hunsung.board.entity;

import com.hunsung.board.dto.BoardCreateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Board") //-해당클래스를 Entity클래스로 사용하겠다.
@Table(name = "Board") //-데이터베이스에 있는 해당하는 테이블과 현재 클래스를 매핑시킴
public class BoardEntity {
    @Id//BoardId을 PrimaryKey로 사용한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //value를 자동으로 생성해주는 어노테이션 전략 :
    // 1.strategy -IDENTITY : AUTO_INCREMENT
    //            -SEQUENCE : 오라클,Postgre 시퀀스를 지원
    //            -TABLE : 키 생성 전용 테이블 만들고 이름,값을 만들어서 시퀀스를 흉내내는것
    private int boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardImage;
    private String boardVideo;
    private String boardFile;
    private String boardWriterEmail;
    private String boardWriterProfile;
    private String boardWriterNickname;
    private Timestamp boardWriteDate;
    private int boardClickCount;
    private int boardLikeCount;
    private int boardCommentCount;

    public BoardEntity(BoardCreateDto dto){
        this.boardTitle = dto.getBoardTitle();
        this.boardContent = dto.getBoardContent();
        this.boardImage = dto.getBoardImage();
        this.boardVideo = dto.getBoardVideo();
        this.boardFile = dto.getBoardFile();
        this.boardWriterEmail = dto.getBoardWriterEmail();
        this.boardWriterProfile = dto.getBoardWriterProfile();
        this.boardWriterNickname = dto.getBoardWriterNickname();
        this.boardWriteDate = dto.getBoardWriteDate();
    }

}
