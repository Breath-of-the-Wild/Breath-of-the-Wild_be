package com.breath_of_the_wild_be.service;


import com.breath_of_the_wild_be.domain.Todo;
import com.breath_of_the_wild_be.dto.PageRequestDTO;
import com.breath_of_the_wild_be.dto.PageResponseDTO;
import com.breath_of_the_wild_be.dto.TodoDTO;
import com.breath_of_the_wild_be.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor  //생성자 자동 주입
public class TodoServiceImpl implements TodoService {

    //자동 주입 대상은 final
    private final ModelMapper modelMapper;
    private final TodoRepository todoRepository;


    //#1. 등록(insert)
    @Override
    public Long register(TodoDTO todoDTO) {

        log.info("등록!!! .........");

        Todo todo = modelMapper.map(todoDTO, Todo.class);

        Todo savedTodo = todoRepository.save(todo);

        return savedTodo.getTno();

    }


    //#2. 조회(select)
    @Override
    public TodoDTO get(Long tno) {

        Optional<Todo> result = todoRepository.findById(tno);
        //Optional :래퍼 클래스
        //          이것을 사용하지 않으면 매번 if문을 이욯새 null인지 체크한 후 사용
        //          이것을 이용하면 null 체크를 위한 if문 없이도 NullPointerException이 발생하지
        //          않음


        Todo todo = result.orElseThrow();

        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);

        return dto;
    }

    //#3. 업데이트(update)
    @Override
    public void modify(TodoDTO todoDTO) {

        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());

        Todo todo = result.orElseThrow();

        todo.changeTitle(todoDTO.getTitle());
        todo.changeDueDate(todoDTO.getDueDate());
        todo.changeComplete(todoDTO.isComplete());

        todoRepository.save(todo);

    }

    //#4. 삭제(delete)
    @Override
    public void remove(Long tno) {

        todoRepository.deleteById(tno);

    }

    //#5. 페이징 처리
    @Override
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {

        Pageable pageable =
                PageRequest.of(
                        pageRequestDTO.getPage() - 1,
                        pageRequestDTO.getSize(),
                        Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);

        List<TodoDTO> dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDTO.class))
                .collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponseDTO<TodoDTO> responseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();

        return responseDTO;
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        return null;
    }
}
