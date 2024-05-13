package com.breath_of_the_wild_be.repository;

import com.breath_of_the_wild_be.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
