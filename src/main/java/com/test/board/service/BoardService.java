package com.test.board.service;

import com.test.board.model.Board;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface BoardService {

    List<Board> getAllBoard();

    Board save(Board board);

    ResponseEntity<Board> findById(Integer no) throws Exception;

    ResponseEntity<Board> updateBoard(Integer no, Board board);

    ResponseEntity<Map<String,Boolean>> deleteBoard(Integer no);
}
