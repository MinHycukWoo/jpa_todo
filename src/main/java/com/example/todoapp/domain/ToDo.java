package com.example.todoapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.context.annotation.Primary;

@Entity(name="ToDo") //엔티티명
@Table(name = "todo") //테이블명
@AllArgsConstructor //모든걸 받은 생성자 생성
@NoArgsConstructor //아무것도 안받는 생성자
@Setter
@Getter
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String todo;
}
