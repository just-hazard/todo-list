package me.leo.todolist.controllers

import me.leo.todolist.models.Todo
import me.leo.todolist.services.TodoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class TodoController(private val todoService: TodoService) {
    @GetMapping("/todos")
    fun getTodoList() : List<Todo> {
        return todoService.getTodoList()
    }

    @PostMapping("/todos")
    @ResponseStatus(HttpStatus.CREATED)
    fun createTodo(@RequestBody todo: Todo): Todo {
        return todoService.createTodo(todo)
    }

    @PutMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    fun updateTodo(@RequestBody todo: Todo): Todo {
        return todoService.updateTodo(todo)
    }

}