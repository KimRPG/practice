package com.hunsung.board.controller;

import com.hunsung.board.dto.BoardCreateDto;
import com.hunsung.board.dto.ResponseDto;
import com.hunsung.board.entity.BoardEntity;
import com.hunsung.board.entity.PopularSearchEntity;
import com.hunsung.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/board")
public class BoardController {
        @GetMapping("/")
    public String getBoard(@AuthenticationPrincipal String userEmail){
        return "로그인 된 사용자는 " +userEmail+" 입니다.";
    }
    @Autowired
    BoardService boardService;

    @GetMapping("/top3")
    public ResponseDto<List<BoardEntity>> getTop3() {
        return boardService.getTop3();
    }

    @GetMapping("/List")
    public ResponseDto<List<BoardEntity>> getList() {
        return boardService.getList();
    }

    @GetMapping("/popularList")
    public ResponseDto<List<PopularSearchEntity>> getPopularList() {

        return boardService.getPopularList();
    }

    @PostMapping("/create")
    public ResponseDto<?> boardCreate(@RequestBody BoardCreateDto requestBody) {
        ResponseDto<?> result = boardService.createBoard(requestBody);
        return result;
    }

    @GetMapping("/AllList")
    public ResponseDto<List<BoardEntity>> getAllList() {
        return boardService.getAllList();
    }

    @GetMapping("/update/{id}")
    public ResponseDto<?> boardUpdate(@PathVariable("id") Integer id, @RequestBody BoardCreateDto requestBody) {
        return boardService.boardUpdate(id, requestBody);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto<?> boardDelete(@PathVariable("id") Integer id) {
        return boardService.boardDelete(id);
    }

    @GetMapping("/search/{boardTitle}")
    public ResponseDto<List<BoardEntity>> getSearchList(@PathVariable("boardTitle") String title) {
        return boardService.getSearchList(title);
    }


}
