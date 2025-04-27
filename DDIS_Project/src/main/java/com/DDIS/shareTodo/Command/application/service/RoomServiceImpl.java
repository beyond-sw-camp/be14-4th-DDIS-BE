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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ShareTodoRepository shareTodoRepository;
    private final MemberShareTodoRepository memberShareTodoRepository;
    private final GptService gptService;
    ModelMapper modelMapper;


    private static final List<String> colorPalette = List.of(
            "#FF6D7F", "#505FD4", "#50D4C6"
    );

    private String pickRandomColor() {
        return colorPalette.get(new Random().nextInt(colorPalette.size()));
    }


    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository,
                           PostRepository postRepository,
                           MemberRepository memberRepository,
                           ShareTodoRepository shareTodoRepository,
                           MemberShareTodoRepository memberShareTodoRepository,
                           GptService gptService,
                           ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.shareTodoRepository = shareTodoRepository;
        this.memberShareTodoRepository = memberShareTodoRepository;
        this.gptService = gptService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public List<ShareTodo> createRoom(CreateShareRoomDTO roomDTO) {
        String randomColor = pickRandomColor();
        Long postNum = roomDTO.getPostNum();
        Post posts = postRepository.findById(postNum).orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));


        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endDate = now.plusDays(posts.getActivityTime());
        String formattedenddate = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        Rooms rooms = Rooms.builder().
                roomNum(postNum).
                memberCount(roomDTO.getMemberCount()).
                colorRgb(randomColor).
                startDate(formatted).
                endDate(formattedenddate).
                title(posts.getPostTitle()).
                content(posts.getPostContent()).build();

        roomRepository.save(rooms);


        List<ShareTodo> todoList = gptService.generateTodoList(posts.getPostTitle());


        return todoList;

        }

    @Override
    @Transactional
    public void saveShareTodos(List<SaveShareTodoDTO> todoList) {
        if (todoList.isEmpty()) {
            throw new IllegalArgumentException("저장할 Todo가 없습니다.");
        }
        Rooms room = roomRepository.findById(postNum)
                .orElseThrow(() -> new IllegalArgumentException("방을 찾을 수 없습니다."));

        // 저장된 ShareTodo 리스트 모으기
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
        

        // (여기!) 저장된 Todo를 멤버에게 할당
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



