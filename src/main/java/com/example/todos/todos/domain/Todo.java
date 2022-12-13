package com.example.todos.todos.domain;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    String title;

    @Setter
    Integer todoOrder;

    @Setter
    boolean completed;

    public Todo(String title, Integer todoOrder, boolean completed) {
        this.title = title;
        this.todoOrder = todoOrder;
        this.completed = completed;
    }

    public static Todo of(String title, Integer todoOrder, boolean completed){
        return new Todo(title, todoOrder, completed);
    }

}
