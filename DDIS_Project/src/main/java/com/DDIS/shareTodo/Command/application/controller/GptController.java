package com.DDIS.shareTodo.Command.application.controller;

import com.DDIS.approve.Command.application.dto.GenerateTodoRequest;
import com.DDIS.shareTodo.Command.application.service.GptService;
import com.DDIS.shareTodo.Command.application.service.RoomService;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.ShareTodo;
import com.DDIS.shareTodo.Command.domain.repository.ShareTodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gpt")
@RequiredArgsConstructor
public class GptController {

    private final GptService gptService;
    private final RoomService roomService;
    private final ShareTodoRepository shareTodoRepository;

    @PostMapping("/generate-todos")
    public ResponseEntity<List<ShareTodo>> generateAndAssignTodos(@RequestBody GenerateTodoRequest request) {
        String topic = request.getTopic();
        Long roomNum = request.getRoomNum();

        // ✅ 1. 방 조회
        Rooms room = roomService.findRoomByRoomNum(roomNum);

        // ✅ 2. GPT로 Todo 생성
        List<ShareTodo> generatedTodos = gptService.generateTodoList(room, topic);

        // ✅ 3. DB에 저장
        shareTodoRepository.saveAll(generatedTodos);

        // ✅ 4. 저장된 Todo를 멤버들에게 자동 할당
        roomService.assignTodosToMembers(room, generatedTodos);

        // ✅ 5. 최종적으로 저장된 Todo 리스트 반환
        return ResponseEntity.ok(generatedTodos);
    }
}
