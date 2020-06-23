package me.leo.todolist.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import me.leo.todolist.models.Todo
import me.leo.todolist.repositories.TodoRepository
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTests (
        @Autowired private val mockMvc: MockMvc,
        @Autowired private val todoRepository: TodoRepository
): BaseControllerTests  {
    @Test
    fun getTodoList() {
        // given
        // 저장되어져 있는 투두가 존재함
        val todo: Todo = Todo(null,"This is title", "Description!!!", false)
        todoRepository.save(todo)

        // when
        // 요청을 보냈을 때
        val todoApiResult = mockMvc.perform(get("/todos")
                .contentType(MediaType.APPLICATION_JSON))

        // then
        // 투두 리스트가 정상적으로 응답어 옮
        todoApiResult
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.length()", greaterThan(0)))
    }

    @Test
    fun createTodo() {
        // given
        // 저장할 데이터를 생성 (todo 객체)
        val expected: Todo = Todo(null,"This is title", "Description!!!", false)

        val objectMapper: ObjectMapper = ObjectMapper()
        val saveResult = objectMapper.writeValueAsString(expected)

        // when
        // todo를 저장하는 api에 request 요청
       val todoPostResult = mockMvc.perform(post("/todos")
               .contentType(MediaType.APPLICATION_JSON)
               .content(saveResult))

        // then
        // response 값을 통해 정상적으로 등록 되었는지 확인
        todoPostResult.andExpect(status().isCreated)
                .andExpect(jsonPath("$.id", `is`(not(emptyOrNullString()))))
                .andExpect(jsonPath("$.title", `is`(expected.title)))
                .andExpect(jsonPath("$.description", `is`(expected.description)))
    }

    @Test
    fun updateTodo() {
        // given
        // 데이터를 하나 넣어두고
        val todo: Todo = Todo(1,"This is title", "Description!!!", false)
        todoRepository.save(todo)

        val updateTodo = Todo(1,"title", "Description", true)

        val objectMapper: ObjectMapper = ObjectMapper()
        val updateResult = objectMapper.writeValueAsString(updateTodo)


        // when
        // 넣어놓은 해당 키값에 데이터를 수정하여 요청
        val todoPutResult = mockMvc.perform(put("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateResult))

        // then
        // 요청한 데이터가 정상적으로 수정이 되었는지 확인
        todoPutResult.andExpect(status().isOk)
                .andExpect(jsonPath("$.title").value(updateTodo.title))
                .andExpect(jsonPath("$.description").value(updateTodo.description))
                .andExpect(jsonPath("$.done").value(true))
    }

}
