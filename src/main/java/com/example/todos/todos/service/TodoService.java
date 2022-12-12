package com.example.todos.todos.service;

import com.example.todos.todos.domain.Todo;
import com.example.todos.todos.dto.TodoDto;
import com.example.todos.todos.dto.request.TodoRequestDto;
import com.example.todos.todos.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository toDoRepository;

    public TodoDto createToDo(TodoRequestDto dto) {
        Todo todos = toDoRepository.save(dto.toEntity());
        return TodoDto.from(todos);
    }

    public TodoDto updateToDo(Long todoId, TodoRequestDto dto) {
        Todo todo = toDoRepository.getReferenceById(todoId);
        if (todo.getId().equals(todoId)) {
            if (dto.title() != null) {
                todo.setTitle(dto.title());
            }
            if (dto.todoOrder() != null) {
                todo.setTodoOrder(dto.todoOrder());
            }

            todo.setCompleted(dto.completed());
        }

        return TodoDto.from(todo);
    }

    @Transactional(readOnly = true)
    public TodoDto getToDo(Long todoId) {
        return toDoRepository.findById(todoId)
                .map(TodoDto::from)
                .orElseThrow(() -> new EntityNotFoundException("Todo 가 없습니다."));
    }

    @Transactional(readOnly = true)
    public List<TodoDto> getToDos() {
        return toDoRepository.findAll()
                .stream()
                .map(TodoDto::from)
                .collect(Collectors.toList());
    }

    public void deleteToDo(Long todoId) {
        toDoRepository.deleteById(todoId);
    }

    public void deleteToDoList() {
        toDoRepository.deleteAll();
    }

}
