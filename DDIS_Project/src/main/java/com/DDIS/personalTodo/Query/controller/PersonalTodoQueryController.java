package com.DDIS.personalTodo.Query.controller;

import com.DDIS.personalTodo.Query.dto.PersonalTodoQueryDTO;
import com.DDIS.personalTodo.Query.service.PersonalTodoQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("personalTodo")
@RequiredArgsConstructor
public class PersonalTodoQueryController {

    private final PersonalTodoQueryService personalTodoQueryService;

    // 1. 회원별 전체 todo 조회
    @GetMapping("/{clientNum}")
    public ResponseEntity<List<PersonalTodoQueryDTO>> getAllTodos(@PathVariable Long clientNum) {
        List<PersonalTodoQueryDTO> todos = personalTodoQueryService.getAllTodos(clientNum);
        return ResponseEntity.ok(todos);
    }

    // 2. 특정 일자별 특정 회원의 todo 조회
    @GetMapping("/{clientNum}/date")
    public ResponseEntity<List<PersonalTodoQueryDTO>> getTodosByDate(@PathVariable Long clientNum,
                                                                     @RequestParam String todoDate) {
        List<PersonalTodoQueryDTO> todos = personalTodoQueryService.getTodosByDate(clientNum, todoDate);
        return ResponseEntity.ok(todos);
    }

    // 3. 카테고리 별 특정 회원의 todo 조회
    @GetMapping("/{clientNum}/category/{categoryNum}")
    public ResponseEntity<List<PersonalTodoQueryDTO>> getTodosByCategory(@PathVariable Long clientNum,
                                                                         @PathVariable Long categoryNum) {
        List<PersonalTodoQueryDTO> todos = personalTodoQueryService.getTodosByCategory(clientNum, categoryNum);
        return ResponseEntity.ok(todos);
    }

    // 4. 핀 고정된 todo 조회
    @GetMapping("/{clientNum}/pinned")
    public ResponseEntity<List<PersonalTodoQueryDTO>> getPinnedTodos(@PathVariable Long clientNum) {
        List<PersonalTodoQueryDTO> todos = personalTodoQueryService.getPinnedTodos(clientNum);
        return ResponseEntity.ok(todos);
    }

    // 5, 6번 공개/비공개 TODO 조회
    @GetMapping("/{clientNum}/visibility")
    public ResponseEntity<List<PersonalTodoQueryDTO>> getTodosByVisibility(@PathVariable Long clientNum,
                                                                           @RequestParam Boolean isPublic) {
        List<PersonalTodoQueryDTO> todos = personalTodoQueryService.getTodosByVisibility(clientNum, isPublic);
        return ResponseEntity.ok(todos);
    }

    // 7, 8번 완료/미완료 된 todo 조회
    @GetMapping("/{clientNum}/donestatus")
    public ResponseEntity<List<PersonalTodoQueryDTO>> getTodosByDoneStatus(@PathVariable Long clientNum,
                                                                           @RequestParam Boolean isDone) {
        List<PersonalTodoQueryDTO> todos = personalTodoQueryService.getTodosByDoneStatus(clientNum, isDone);
        return ResponseEntity.ok(todos);
    }
}
