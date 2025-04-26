package com.DDIS.personalTodo.Query.controller;

import com.DDIS.personalTodo.Query.dto.PersonalTodoQueryDTO;
import com.DDIS.personalTodo.Query.service.PersonalTodoQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personalTodo")
@RequiredArgsConstructor
public class PersonalTodoQueryController {

    private final PersonalTodoQueryService personalTodoQueryService;

    // 1. 회원별 전체 todo 조회
    @GetMapping("/{clientNum}")
    public List<PersonalTodoQueryDTO> getAllTodos(@PathVariable Long clientNum) {
        return personalTodoQueryService.getAllTodos(clientNum);
    }

    // 2. 특정 일자별 특정 회원의 todo 조회
    @GetMapping("/{clientNum}/date")
    public List<PersonalTodoQueryDTO> getTodosByDate(@PathVariable Long clientNum,
                                             @RequestParam String todoDate) {
        return personalTodoQueryService.getTodosByDate(clientNum, todoDate);
    }

    // 3. 카테고리 별 특정 회원의 todo 조회
    @GetMapping("/{clientNum}/category/{categoryNum}")
    public List<PersonalTodoQueryDTO> getTodosByCategory(@PathVariable Long clientNum,
                                                 @PathVariable Long categoryNum) {
        return personalTodoQueryService.getTodosByCategory(clientNum, categoryNum);
    }

    // 4. 핀 고정된 todo 조회
    @GetMapping("/{clientNum}/pinned")
    public List<PersonalTodoQueryDTO> getPinnedTodos(@PathVariable Long clientNum) {
        return personalTodoQueryService.getPinnedTodos(clientNum);
    }

    // 5, 6번 공개/비공개 TODO 조회
    @GetMapping("/{clientNum}/visibility")
    public List<PersonalTodoQueryDTO> getTodosByVisibility(@PathVariable Long clientNum,
                                                           @RequestParam Boolean isPublic) {
        return personalTodoQueryService.getTodosByVisibility(clientNum, isPublic);
    }

    // 7, 8번 완료/미완료 된 todo 조회
    @GetMapping("/{clientNum}/donestatus")
    public List<PersonalTodoQueryDTO> getTodosByDoneStatus(@PathVariable Long clientNum,
                                                           @RequestParam Boolean isDone) {
        return personalTodoQueryService.getTodosByDoneStatus(clientNum, isDone);
    }
}
