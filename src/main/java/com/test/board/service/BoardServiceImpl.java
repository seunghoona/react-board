package com.test.board.service;

import com.test.board.exception.ResourceNotFoundException;
import com.test.board.model.Board;
import com.test.board.repository.BoardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<Board> getAllBoard() {
        return boardRepository.findAll();
    }

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public ResponseEntity<Board> findById(Integer no) throws Exception {
        Optional<Board> byId = boardRepository.findById(no);
        Board board = byId.orElseThrow(()-> new ResourceNotFoundException("해당 게시글은 삭제 되었습니다."));
        return ResponseEntity.ok(board);
    }

    @Override
    public ResponseEntity<Board> updateBoard(Integer no, Board board) {

        Optional<Board> byId = boardRepository.findById(no);

        Board board1 = byId.orElseThrow(() -> new ResourceNotFoundException("존재하지 않는 게시글입니다."));

        BeanUtils.copyProperties(board, board1,"no");


        Board endUpdatedBoard = boardRepository.save(board1);
        return ResponseEntity.ok(endUpdatedBoard);
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteBoard(Integer no) {


        Optional<Board> byId = boardRepository.findById(no);
        Board board = byId.orElseThrow(() -> new ResourceNotFoundException("삭제할 대상 글이 없습니다."));

        boardRepository.deleteById(board.getNo());

        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("DELETE BOARD " + board.getNo(), Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
