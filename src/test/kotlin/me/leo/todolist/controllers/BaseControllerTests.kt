package me.leo.todolist.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import me.leo.todolist.models.Todo
import org.junit.jupiter.api.Assertions
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import kotlin.reflect.full.memberProperties

interface BaseControllerTests {

//    fun ResultActions.assertResult(expected: Todo) {
//        val a = expected::class
//
//        a.memberProperties.forEach {
//            if (it.name == "id") return@forEach
//            println(it.name)
//
//            this.andExpect(
//                    jsonPath("$." + it.name)
//                            .value(
//                                    it.getter.call(args = *arrayOfNulls(1))!!));
//        }
//    }
}