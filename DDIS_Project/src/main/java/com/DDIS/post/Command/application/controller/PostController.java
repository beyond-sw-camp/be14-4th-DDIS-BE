package com.DDIS.post.Command.application.controller;

import com.DDIS.post.Command.domain.aggregate.dto.PostCreateRequestDTO;
import com.DDIS.post.Command.domain.aggregate.dto.PostResearchDTO;
import com.DDIS.post.Command.domain.aggregate.dto.PostResponseDTO;
import com.DDIS.post.Command.domain.aggregate.dto.PostUpdateRequestDTO;
import com.DDIS.post.Command.domain.aggregate.entity.Post;
import com.DDIS.post.Command.application.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("postCommandController")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping("/post")
@Slf4j
public class PostController {

    private final PostService postService;

    // 1. 조회
    @GetMapping("/{postNum}")
    public ResponseEntity<PostResponseDTO> getPrivatePost(
            @PathVariable Long postNum,
            @RequestParam(required = false) String password
    ) {
        log.info("비공개 게시글 조회 요청 - postNum: {}, 입력된 비밀번호: {}", postNum, password);

        Post post = postService.getPost(postNum, password);
        return ResponseEntity.ok(PostResponseDTO.fromEntity(post));
    }

    // 2. 작성
    @PostMapping("/createPost")
    public ResponseEntity<String> createPost(@RequestBody PostCreateRequestDTO dto) {
        postService.createPost(dto);
        return ResponseEntity.ok("게시글 작성 완료");

    }

    // 3. 수정
    @PatchMapping("/{postNum}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postNum,
                                           @RequestBody PostUpdateRequestDTO requestDTO) {
        postService.updatePost(postNum, requestDTO, null);
        return ResponseEntity.ok().build();
    }

    // 4. 삭제
    @DeleteMapping("/{postNum}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postNum) {

        postService.deletePost(postNum, null);
        return ResponseEntity.ok().build();
    }

    // 5. 검색
    @GetMapping("/search")
    public ResponseEntity<List<PostResearchDTO>> searchPosts(@RequestParam String keyword) {
        List<PostResearchDTO> results = postService.searchPosts(keyword);
        return ResponseEntity.ok(results);
    }
}
