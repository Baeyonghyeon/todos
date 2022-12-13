package com.example.todos.todos.dto;

import com.example.todos.todos.domain.Todo;

import java.io.Serializable;

public record TodoDto(
        Long id,
        String title,
        Integer todoOrder,
        boolean completed
)
        implements Serializable
{

    public static TodoDto of(String title, Integer todoOrder, boolean completed) {
        return new TodoDto(null, title, todoOrder, completed);
    }

    public static TodoDto from(Todo entity) {
        return new TodoDto(
                entity.getId(),
                entity.getTitle(),
                entity.getTodoOrder(),
                entity.isCompleted()
        );
    }

}
