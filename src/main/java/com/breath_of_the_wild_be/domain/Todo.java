package com.breath_of_the_wild_be.domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tbl_todo")
public class Todo {
    @Id    //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

      private String title;

    private String writer;
    private boolean complete;
    private LocalDate dueDate;

//    public void setTitle(String title){
//        this.title =  title;
//    }
//
//    public void setWriter(String writer){
//        this.writer = writer;
//    }

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeComplete(boolean complete){
        this.complete = complete;
    }

    public void changeDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

}