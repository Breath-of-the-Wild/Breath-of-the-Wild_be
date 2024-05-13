package com.breath_of_the_wild_be.repository.search;

import com.breath_of_the_wild_be.domain.Todo;
import com.breath_of_the_wild_be.dto.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface TodoSearch {

    Page<Todo> search1(PageRequestDTO pageRequestDTO);
}
