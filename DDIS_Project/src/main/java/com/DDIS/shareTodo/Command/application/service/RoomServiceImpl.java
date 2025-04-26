package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.approve.Command.domain.repository.MemberRepository;
import com.DDIS.approve.Command.domain.repository.MemberShareTodoRepository;
import com.DDIS.shareTodo.Command.application.dto.CreateShareRoomDTO;
import com.DDIS.shareTodo.Command.application.dto.SaveShareTodoDTO;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.*;
import com.DDIS.shareTodo.Command.domain.repository.PostRepository;
import com.DDIS.shareTodo.Command.domain.repository.RoomRepository;
import com.DDIS.shareTodo.Command.domain.repository.SharetodoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final SharetodoRepository shareTodoRepository;
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
                           SharetodoRepository shareTodoRepository,
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
        Posts posts = postRepository.findById(postNum).orElseThrow(() -> new IllegalArgumentException("해당 게시글 없음"));


        LocalDateTime now = LocalDateTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endDate = now.plusDays(posts.getActivityTime());
        String formattedenddate = endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        Rooms rooms = Rooms.builder().
                memberCount(roomDTO.getMemberCount()).
                colorRgb(randomColor).
                startDate(formatted).
                endDate(formattedenddate).
                title(posts.getPostTitle()).
                content(posts.getPostContent()).build();

        roomRepository.save(rooms);


        List<ShareTodo> todoList = gptService.generateTodoList(posts.getPostTitle());


        return todoList;


//        // (4) 방 생성 후, 해당 방 멤버 조회
//        List<Members> members = memberRepository.findByRoom_RoomNum(rooms.getRoomNum());
//
//        // (5) 이 방에 연결된 share_todos 조회
//        List<ShareTodo> shareTodos = shareTodoRepository.findByPost_PostNum(postNum);
//
//        // (6) 멤버 × share_todo로 member_share_todos insert
//        for (Members member : members) {
//            for (ShareTodo shareTodo : shareTodos) {
//                MemberShareTodo memberShareTodo = MemberShareTodo.builder()
//                        .memberNum(member)
//                        .shareTodoNum(shareTodo)
//                        .isCompleted(false)
//                        .completionTime(null)
//                        .build();
//                memberShareTodoRepository.save(memberShareTodo);
//            }
        }

    @Override
    @Transactional
    public void saveShareTodos(List<SaveShareTodoDTO> todoList) {
        for (SaveShareTodoDTO dto : todoList) {
            Posts post = postRepository.findById(dto.getPostNum())
                    .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

            ShareTodo shareTodo = ShareTodo.builder()
                    .shareTodoName(dto.getShareTodoName())
                    .post(post)
                    .pinOrder(dto.getPinOrder())
                    .build();

            shareTodoRepository.save(shareTodo);
        }
    }




    }



