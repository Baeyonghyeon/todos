package com.example.todos.todos.dto.request;

import com.example.todos.todos.domain.Todo;
import com.example.todos.todos.dto.TodoDto;

public record TodoRequestDto(
        String title,
        Integer todoOrder,
        boolean completed)

{

    public Todo toEntity() {
        return Todo.of(
                title,
                todoOrder,
                completed
        );
    }

}
