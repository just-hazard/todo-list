package me.leo.todolist.services

import me.leo.todolist.models.Todo
import me.leo.todolist.repositories.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(private val todoRepository: TodoRepository) {
    fun getTodoList(): List<Todo> {
        return todoRepository.findAll()
    }

    fun createTodo(todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    fun updateTodo(todo: Todo): Todo {
        return todoRepository.save(todo)
    }
}
