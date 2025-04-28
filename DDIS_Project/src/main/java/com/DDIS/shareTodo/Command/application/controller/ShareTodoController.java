package com.DDIS.shareTodo.Command.application.controller;


import com.DDIS.shareTodo.Command.application.dto.UpdateShareTodoDTO;
import com.DDIS.shareTodo.Command.application.service.ShareTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/share-todo")
@RequiredArgsConstructor
public class ShareTodoController {

    private final ShareTodoService shareTodoService;

    @PatchMapping("/{shareTodoNum}")
    public ResponseEntity<String> updateShareTodo(
            @PathVariable Long shareTodoNum,
            @RequestBody UpdateShareTodoDTO dto
    ) {
        shareTodoService.updateShareTodo(shareTodoNum, dto);
        return ResponseEntity.ok("공동Todo 수정 완료!");
    }

}
