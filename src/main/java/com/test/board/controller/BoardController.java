package com.test.board.controller;


import com.test.board.model.Board;
import com.test.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BoardController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("/")
    public String home() {
        return "hello world spring";
    }

    @GetMapping("/board")
    public List<Board> getAllBoards() {
        return boardService.getAllBoard();
    }

    @PostMapping("/board")
    public Board createBoard(@RequestBody Board board) {
        return boardService.save(board);
    }

    @GetMapping("/board/{no}")
    public ResponseEntity<Board> createBoard(@PathVariable(value = "no" )  Integer no ) throws Exception {
        return boardService.findById(no);
    }

    @PutMapping("/board/{no}")
    public ResponseEntity<Board> updateBoard(@PathVariable(value = "no") Integer no,
                                             @RequestBody Board board) {
        return boardService.updateBoard(no, board);
    }

    @DeleteMapping("/board/{no}")
    public ResponseEntity<Map<String,Boolean>> deleteBoard(@PathVariable(value="no") Integer no ){

        return boardService.deleteBoard(no);
    }
}
