package com.breath_of_the_wild_be.service;


import com.breath_of_the_wild_be.dto.PageRequestDTO;
import com.breath_of_the_wild_be.dto.PageResponseDTO;
import com.breath_of_the_wild_be.dto.TodoDTO;

public interface TodoService {
    //crud, pageing
    //#1. 등록 기능
    Long register(TodoDTO todoDTO);

    //#2. 조회
    TodoDTO get(Long tno);


    //#3. 업데이트(=갱신)
    void modify(TodoDTO todoDTO);

    //#4. 삭제
    void remove(Long tno);


    //#5. 페이징
    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);



}
