package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.approve.Command.domain.repository.MemberRepository;
import com.DDIS.chatRoom.Command.application.dto.ChatRoomRequestDTO;
import com.DDIS.chatRoom.Command.application.service.ChatRoomService;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomUserEntity;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomRepository;
import com.DDIS.chatRoom.Command.domain.repository.ChatRoomUserRepository;
import com.DDIS.post.Command.domain.aggregate.entity.Post;
import com.DDIS.post.Command.domain.repository.PostRepository;
import com.DDIS.shareTodo.Command.application.dto.CreateShareRoomDTO;
import com.DDIS.shareTodo.Command.application.dto.MemberShareTodoResponseDTO;
import com.DDIS.shareTodo.Command.application.dto.SaveShareTodoDTO;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.*;
import com.DDIS.shareTodo.Command.domain.repository.MemberShareTodoRepository;
import com.DDIS.shareTodo.Command.domain.repository.RoomRepository;
import com.DDIS.shareTodo.Command.domain.repository.ShareTodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ShareTodoRepository shareTodoRepository;
    private final MemberShareTodoRepository memberShareTodoRepository;
    private final GptService gptService;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;

    private static final List<String> colorPalette = List.of(
            "#FF6D7F", "#505FD4", "#50D4C6"
    );

    private String pickRandomColor() {
        return colorPalette.get(new Random().nextInt(colorPalette.size()));
    }

    // 공투방 생성
    @Override
    @Transactional
    public Rooms createRoom(CreateShareRoomDTO roomDTO) {
        String randomColor = pickRandomColor();
        Long postNum = roomDTO.getPostNum();
        Post posts = postRepository.findById(postNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));

        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endDate = now.plusDays(posts.getActivityTime());
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Rooms rooms = Rooms.builder()
                .roomNum(postNum)
                .memberCount(roomDTO.getMemberCount())
                .colorRgb(randomColor)
                .startDate(formatted)
                .endDate(formattedEndDate)
                .title(posts.getPostTitle())
                .content(posts.getPostContent())
                .build();

        roomRepository.save(rooms);

        // ✅ ChatRoom 생성
        ChatRoomEntity chatRoom = createChatRoom(rooms);

        // ✅ ChatRoomUser 등록 (방 만든 사람을 등록)
        createChatRoomUser(chatRoom, roomDTO.getClientNum());

        return rooms;
    }

    // ChatRoom 생성
    private ChatRoomEntity  createChatRoom(Rooms rooms) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ChatRoomEntity chatRoom = new ChatRoomEntity();
        chatRoom.setChatRoomName(rooms.getTitle());
        chatRoom.setChatRoomType("공동"); // 필요시 다른 타입으로 변경 가능
        chatRoom.setRoomNum(rooms);
        chatRoom.setCreatedTime(now);

        return chatRoomRepository.save(chatRoom); // 저장하고 리턴
    }

    private void createChatRoomUser(ChatRoomEntity chatRoom, Long clientNum) {
        ChatRoomUserEntity chatRoomUser = ChatRoomUserEntity.builder()
                .chatRoom(chatRoom)
                .clientNum(clientNum)
                .role("회원")
                .lastMsgNum(null) // 아직 읽은 메시지 없음
                .build();

        chatRoomUserRepository.save(chatRoomUser);
    }


    @Override
    @Transactional
    public void saveShareTodos(List<SaveShareTodoDTO> todoList) {
        if (todoList.isEmpty()) {
            throw new IllegalArgumentException("저장할 Todo가 없습니다.");
        }
        Long roomNum = todoList.get(0).getRoomNum();
        Rooms room = roomRepository.findById(roomNum)
                .orElseThrow(() -> new IllegalArgumentException("방을 찾을 수 없습니다."));

        List<ShareTodo> savedTodos = new ArrayList<>();
        for (SaveShareTodoDTO dto : todoList) {
            ShareTodo shareTodo = ShareTodo.builder()
                    .shareTodoName(dto.getShareTodoName())
                    .rooms(room)
                    .build();
            shareTodoRepository.save(shareTodo);
            savedTodos.add(shareTodo);
        }

        assignTodosToMembers(room, savedTodos);
    }

    @Override
    public Rooms findRoomByRoomNum(Long roomNum) {
        return roomRepository.findById(roomNum)
                .orElseThrow(() -> new IllegalArgumentException("방을 찾을 수 없습니다."));
    }

    @Override
    @Transactional
    public List<MemberShareTodoResponseDTO> generateAndSaveGptTodos(Long roomNum, String topic) {
        Rooms room = findRoomByRoomNum(roomNum);

        // GPT한테 투두 생성
        List<ShareTodo> gptTodos = gptService.generateTodoList(room, topic);

        // 생성한 투두를 저장
        List<ShareTodo> savedTodos = new ArrayList<>();
        for (ShareTodo todo : gptTodos) {
            ShareTodo newTodo = ShareTodo.builder()
                    .shareTodoName(todo.getShareTodoName())
                    .rooms(room)
                    .build();
            shareTodoRepository.save(newTodo);
            savedTodos.add(newTodo);
        }

        List<MemberShareTodo> memberShareTodos = assignTodosToMembers(room, savedTodos);



        return memberShareTodos.stream()
                .map(mst -> MemberShareTodoResponseDTO.builder()
                        .memberShareTodoNum(mst.getMemberShareTodoNum())  // 진짜 저장된 PK
                        .shareTodoNum(mst.getShareTodo().getShareTodoNum())
                        .shareTodoName(mst.getShareTodo().getShareTodoName()).build())
                .toList();
    }

    private List<MemberShareTodo> assignTodosToMembers(Rooms room, List<ShareTodo> shareTodos) {
        List<Members> members = memberRepository.findByRoom_RoomNum(room.getRoomNum());
        List<MemberShareTodo> result = new ArrayList<>();

        for (Members member : members) {
            for (ShareTodo todo : shareTodos) {
                MemberShareTodo memberShareTodo = MemberShareTodo.builder()
                        .memberNum(member)
                        .shareTodo(todo)
                        .build();
                memberShareTodoRepository.save(memberShareTodo);
                result.add(memberShareTodo);
            }
        }
        return result;
    }
}
