package com.DDIS.personalTodo.Command.application.controller;

import com.DDIS.personalTodo.Command.application.dto.request.PersonalTodoCreateRequestDTO;
import com.DDIS.personalTodo.Command.application.dto.request.PersonalTodoUpdateRequestDTO;
import com.DDIS.personalTodo.Command.application.service.PersonalTodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal-todo")
@RequiredArgsConstructor
public class PersonalTodoController {

    private final PersonalTodoService personalTodoService;

    // Todo 생성
    @PostMapping
    public ResponseEntity<Void> createPersonalTodo(
            @Valid @RequestBody PersonalTodoCreateRequestDTO requestDTO,
            @RequestParam Long clientNum){
        // 추후 로그인 확장
//            @AuthenticationPrincipal CustomUserDetails userDetails) {

//        Long clientNum = userDetails.getClientNum();

        personalTodoService.createPersonalTodo(requestDTO, clientNum);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Todo 수정
    @PatchMapping
    public ResponseEntity<Void> updatePersonalTodo(@Valid @RequestBody PersonalTodoUpdateRequestDTO requestDTO,
                                                   @RequestParam Long clientNum) {
        personalTodoService.updatePersonalTodo(requestDTO, clientNum);
        return ResponseEntity.ok().build();
    }
}