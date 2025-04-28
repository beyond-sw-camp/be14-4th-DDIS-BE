package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.approve.Command.domain.repository.MemberRepository;
import com.DDIS.post.Command.domain.aggregate.entity.Post;
import com.DDIS.post.Command.domain.repository.PostRepository;
import com.DDIS.shareTodo.Command.application.dto.CreateShareRoomDTO;
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

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ShareTodoRepository shareTodoRepository;
    private final MemberShareTodoRepository memberShareTodoRepository;
    private final GptService gptService;

    private static final List<String> colorPalette = List.of(
            "#FF6D7F", "#505FD4", "#50D4C6"
    );

    private String pickRandomColor() {
        return colorPalette.get(new Random().nextInt(colorPalette.size()));
    }

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
        return rooms;
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
                    .pinOrder(dto.getPinOrder())
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
    public void generateAndSaveGptTodos(Long roomNum, String topic) {
        Rooms room = findRoomByRoomNum(roomNum);

        // GPT한테 투두 생성
        List<ShareTodo> gptTodos = gptService.generateTodoList(room, topic);

        // 생성한 투두를 저장
        List<ShareTodo> savedTodos = new ArrayList<>();
        for (ShareTodo todo : gptTodos) {
            ShareTodo newTodo = ShareTodo.builder()
                    .shareTodoName(todo.getShareTodoName())
                    .rooms(room)
                    .pinOrder(todo.getPinOrder())
                    .build();
            shareTodoRepository.save(newTodo);
            savedTodos.add(newTodo);
        }

        // 멤버에게 분배
        assignTodosToMembers(room, savedTodos);
    }

    private void assignTodosToMembers(Rooms room, List<ShareTodo> shareTodos) {
        List<Members> members = memberRepository.findByRoom_RoomNum(room.getRoomNum());

        for (Members member : members) {
            for (ShareTodo todo : shareTodos) {
                MemberShareTodo memberShareTodo = MemberShareTodo.builder()
                        .memberNum(member)
                        .shareTodoNum(todo)
                        .isCompleted(false)
                        .completionTime(null)
                        .build();
                memberShareTodoRepository.save(memberShareTodo);
            }
        }
    }
}
