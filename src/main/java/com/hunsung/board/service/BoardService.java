package com.hunsung.board.service;

import com.hunsung.board.dto.BoardCreateDto;
import com.hunsung.board.dto.ResponseDto;
import com.hunsung.board.entity.BoardEntity;
import com.hunsung.board.entity.PopularSearchEntity;
import com.hunsung.board.repository.BoardRepository;
import com.hunsung.board.repository.PopularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BoardService {
    @Autowired BoardRepository boardRepository;
    @Autowired PopularRepository popularRepository;

    public ResponseDto<List<BoardEntity>> getTop3(){
        List<BoardEntity> boardList = new ArrayList<>();
        Date date = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
        try {
            boardList = boardRepository.findTop3ByBoardWriteDateAfterOrderByBoardLikeCountDesc(date);
            return ResponseDto.setSuccess("Success", boardList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("DB Error");
        }
    }
    public ResponseDto<List<BoardEntity>> getList(){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();

        try{
            boardList = boardRepository.findByOrderByBoardWriteDateDesc();
            return ResponseDto.setSuccess("Success", boardList);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("DB Error");
        }
    }
    public ResponseDto<List<PopularSearchEntity>> getPopularList(){
        List<PopularSearchEntity> popularSearchList = new ArrayList<PopularSearchEntity>();

        try {
            popularSearchList = popularRepository.findTop10ByOrderByPopularSearchCountDesc();
            return ResponseDto.setSuccess("success", popularSearchList);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("DB Error");
        }
    }

    public ResponseDto<?> createBoard(BoardCreateDto dto){
        BoardEntity boardEntity = new BoardEntity(dto);
        try{
            boardRepository.save(boardEntity);
        }catch(Exception e){
            return  ResponseDto.setFailed("Database Error!");
        }

        return ResponseDto.setSuccess("Board Create Success",null);
    }
    public ResponseDto<List<BoardEntity>> getAllList(){
        List<BoardEntity> boardList = new ArrayList<BoardEntity>();
        try {
            boardList = boardRepository.findAll();
            return ResponseDto.setSuccess("Success!", boardList);
        }catch (Exception e){
            return ResponseDto.setFailed("DB error");
        }
    }
    public ResponseDto<?> boardUpdate(Integer id, BoardCreateDto dto){
        BoardEntity boardEntity = boardRepository.findById(id).get();
    try {
        if (dto.getBoardTitle() != null) boardEntity.setBoardTitle(dto.getBoardTitle());
        if (dto.getBoardContent() != null) boardEntity.setBoardContent(dto.getBoardContent());
        if (dto.getBoardImage() != null) boardEntity.setBoardImage(dto.getBoardImage());
        if (dto.getBoardVideo() != null) boardEntity.setBoardVideo(dto.getBoardVideo());
        if (dto.getBoardFile() != null) boardEntity.setBoardFile(dto.getBoardFile());
        if (dto.getBoardWriterEmail() != null) boardEntity.setBoardWriterEmail(dto.getBoardWriterEmail());
        if (dto.getBoardWriterProfile() != null) boardEntity.setBoardWriterProfile(dto.getBoardWriterProfile());
        if (dto.getBoardWriterNickname() != null) boardEntity.setBoardWriterNickname(dto.getBoardWriterNickname());
        if (dto.getBoardWriteDate() != null) boardEntity.setBoardWriteDate(dto.getBoardWriteDate());

        return ResponseDto.setSuccess("Board update Success",null);
    }catch (Exception e){
        return ResponseDto.setFailed("DB Error");
    }
    }
    public ResponseDto<?> boardDelete(Integer id){
        try{
            if(!boardRepository.existsById(id)){
                    return ResponseDto.setFailed("No exists");
            }
            boardRepository.deleteById(id);
            return ResponseDto.setSuccess("Board Delete Success", null);
        }catch (Exception e){
            return ResponseDto.setFailed("DB Error");
        }

    }

    public ResponseDto<List<BoardEntity>> getSearchList(String boardTitle) {

        List<BoardEntity> boardList = new ArrayList<BoardEntity>();

        try {
            boardList = boardRepository.findByBoardTitleContains(boardTitle);
            if(boardList.isEmpty()){
                return  ResponseDto.setFailed("No title");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.setFailed("DB Error");
        }
        return ResponseDto.setSuccess("success", boardList);

    }

}
