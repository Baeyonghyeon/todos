package com.example.todos.todos.controller;

import com.example.todos.todos.dto.TodoDto;
import com.example.todos.todos.dto.request.TodoRequestDto;
import com.example.todos.todos.dto.response.MultiResponseDto;
import com.example.todos.todos.dto.response.SingleResponseDto;
import com.example.todos.todos.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity postTodo(@RequestBody TodoRequestDto todoRequestDto){
        TodoDto createTodoDto = todoService.createToDo(todoRequestDto);

        return new ResponseEntity<>(
                new SingleResponseDto<>(createTodoDto),
                HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateTodo(@PathVariable("id") Long id,
                                     @RequestBody TodoRequestDto requestDto) {
        TodoDto updateTodoDto = todoService.updateToDo(id, requestDto);

        return new ResponseEntity<>(
                new SingleResponseDto<>(updateTodoDto),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity getTodo(@PathVariable("id") Long id) {
        TodoDto todosDto = todoService.getToDo(id);

        return new ResponseEntity<>(
                new SingleResponseDto<>(todosDto),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTodoList(){
        List<TodoDto> dtoList = todoService.getToDos();

        return new ResponseEntity<>(
                new MultiResponseDto<>(dtoList),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTodo(@PathVariable("id") Long id){
        todoService.deleteToDo(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity deleteTodoList(){
        todoService.deleteToDoList();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
