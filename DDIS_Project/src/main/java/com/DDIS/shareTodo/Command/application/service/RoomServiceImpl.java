package com.DDIS.shareTodo.Command.application.service;

import com.DDIS.shareTodo.Command.application.dto.CreateShareRoomDTO;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Posts;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import com.DDIS.shareTodo.Command.domain.repository.PostRepository;
import com.DDIS.shareTodo.Command.domain.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepository;
    PostRepository postRepository;
    ModelMapper modelMapper;


    private static final List<String> colorPalette = List.of(
            "#FF6D7F", "#505FD4", "#50D4C6"
    );

    private String pickRandomColor() {
        return colorPalette.get(new Random().nextInt(colorPalette.size()));
    }


    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createRoom(CreateShareRoomDTO roomDTO) {
        String randomColor = pickRandomColor();
        Integer postNum = roomDTO.getPost().getPostNum();
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




    }


}
