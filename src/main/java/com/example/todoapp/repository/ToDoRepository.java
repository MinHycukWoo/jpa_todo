package com.example.todoapp.repository;

import com.example.todoapp.domain.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    //data jpa 를 사용해서 crud를 간단하게 사용하자
    //JpaRepository<엔티티명, pk의 자료형> 을 선언한다.
}
