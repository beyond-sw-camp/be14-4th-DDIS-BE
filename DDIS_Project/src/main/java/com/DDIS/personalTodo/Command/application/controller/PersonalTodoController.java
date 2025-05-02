package com.DDIS.personalTodo.Command.application.controller;

import com.DDIS.personalTodo.Command.application.dto.request.PersonalTodoCreateRequestDTO;
import com.DDIS.personalTodo.Command.application.dto.request.PersonalTodoUpdateRequestDTO;
import com.DDIS.personalTodo.Command.application.service.PersonalTodoService;
import com.DDIS.security.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal-todos")
@RequiredArgsConstructor
public class PersonalTodoController {

    private final PersonalTodoService personalTodoService;

    // Todo 생성
    @PostMapping
    public ResponseEntity<Void> createPersonalTodo(
            @Valid @RequestBody PersonalTodoCreateRequestDTO requestDTO
           ) {
        Long clientNum = SecurityUtil.getCurrentClientNum();  // 현재 로그인한 사용자의 clientNum 추출
        personalTodoService.createPersonalTodo(requestDTO, clientNum);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Todo 수정
    @PatchMapping
    public ResponseEntity<Void> updatePersonalTodo(@Valid @RequestBody PersonalTodoUpdateRequestDTO requestDTO) {
        Long clientNum = SecurityUtil.getCurrentClientNum();  // 현재 로그인한 사용자의 clientNum 추출
        personalTodoService.updatePersonalTodo(requestDTO, clientNum);
        return ResponseEntity.ok().build();
    }

    // Todo 삭제
    @DeleteMapping
    public ResponseEntity<Void> deletePersonalTodo(
            @RequestParam Long todoNum) {
        Long clientNum = SecurityUtil.getCurrentClientNum();  // 현재 로그인한 사용자의 clientNum 추출
        personalTodoService.deletePersonalTodo(todoNum, clientNum);
        return ResponseEntity.noContent().build();
    }

    // 특정 날짜의 Todo 삭제
    @DeleteMapping("/date")
    public ResponseEntity<Void> deletePersonalTodoDate(
            @RequestParam Long todoNum,
            @RequestParam String todoDate) {
        Long clientNum = SecurityUtil.getCurrentClientNum();  // 현재 로그인한 사용자의 clientNum 추출
        personalTodoService.deletePersonalTodoDate(todoNum, todoDate, clientNum);
        return ResponseEntity.noContent().build();
    }
}
