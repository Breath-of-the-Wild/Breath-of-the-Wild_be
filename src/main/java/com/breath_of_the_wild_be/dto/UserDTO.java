package com.breath_of_the_wild_be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long tno;
    private String title;
    private String writer;
    private boolean complete;

    //날짜 포맷을 설정하는 어노테이션 : 2024-04-29
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;


}
