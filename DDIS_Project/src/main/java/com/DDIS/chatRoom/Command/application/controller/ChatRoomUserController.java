package com.DDIS.chatRoom.Command.application.controller;

import com.DDIS.chatRoom.Command.application.dto.ChatRoomUserEnterDTO;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomUserLeaveDTO;
import com.DDIS.chatRoom.Command.application.service.ChatRoomUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat-rooms/user")
public class ChatRoomUserController {

    private final ChatRoomUserService chatRoomUserService;

    public ChatRoomUserController(ChatRoomUserService chatRoomUserService) {
        this.chatRoomUserService = chatRoomUserService;
    }

    // ✅ 채팅방 입장
    @PostMapping("/enter")
    public String enterChatRoom(@RequestBody ChatRoomUserEnterDTO request) {
        chatRoomUserService.enterChatRoom(request.getChatroomNum(), request.getClientNum(), request.getRole());
        return "채팅방 입장 성공";
    }

    // ✅ 채팅방 퇴장
    public String leaveChatRoom(@RequestBody ChatRoomUserLeaveDTO request) {
        chatRoomUserService.leaveChatRoom(request.getChatroomNum(), request.getClientNum());
        return "채팅방 퇴장 성공";
    }
}