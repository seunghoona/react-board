package com.test.board.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardDTO {

    private Integer no;

    private String type;

    private String title;

    private String contents;

    private Integer memberNo;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private Integer likes;

    private Integer counts;

}
