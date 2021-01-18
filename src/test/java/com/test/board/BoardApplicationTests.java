package com.test.board;

import com.test.board.model.Board;
import com.test.board.model.BoardDTO;
import com.test.board.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BoardApplicationTests {


    @Autowired
    MockMvc mockMvc;


    @Autowired
    BoardRepository boardRepository;


    @Test
    @DisplayName("게시판 입력 테스트 ")
    void creatboard() {

        //given
        BoardDTO boardDTO = BoardDTO.builder()
                .title("제목")
                .contents("내용")
                .counts(1)
                .memberNo(1)
                .createdTime(LocalDateTime.now())
                .build();
        System.out.println("boardDTO.toString() = " + boardDTO.toString());

        //when
        Board board = new Board();
        BeanUtils.copyProperties(boardDTO, board);
        System.out.println("board.toString() = " + board.toString());
        Board save = boardRepository.save(board);

        //then
        Optional<Board> byId = boardRepository.findById(save.getNo());
        Board board1 = byId.orElseThrow(NullPointerException::new);
        assertEquals(board1.getNo(), save.getNo());

    }

    @Test
    void selectBoard() {

        List<Board> all = boardRepository.findAll();

        assertNotNull(all);
    }

    @Test
    @DisplayName("게시판 1개 조회")
    void selectOneBoardDetail() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/board").param("no", "1");

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());

    }
}
