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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody PostCreateRequestDTO dto) {
        Post savedPost = postService.createPost(dto); // 이제 Post 반환받음

        Map<String, Object> response = new HashMap<>();
        response.put("post_num", savedPost.getPostNum());
        response.put("category_name", savedPost.getCategoryNum().getCategoryName());
        response.put("post_title", savedPost.getPostTitle());
        response.put("recruitment_start_date", savedPost.getRecruitmentStartDate());
        response.put("recruitment_end_date", savedPost.getRecruitmentEndDate());

        return ResponseEntity.ok(response); // Vue에서 JSON 파싱 가능
    }


//    @PostMapping("/createPost")
//    public ResponseEntity<Map<String, Object>> createPost(@RequestBody PostCreateRequestDTO postDto) {
//        // 게시글 저장
//        Post savedPost = postService.save(postDto); // ← 실제 저장 로직
//
//        // 응답 JSON 객체 생성
//        Map<String, Object> response = new HashMap<>();
//        response.put("post_num", savedPost.getPostNum());
//        response.put("category_name", savedPost.getCategoryNum().getCategoryName());
//        response.put("post_title", savedPost.getPostTitle());
//        response.put("recruitment_start_date", savedPost.getRecruitmentStartDate());
//        response.put("recruitment_end_date", savedPost.getRecruitmentEndDate());
//
//        return ResponseEntity.ok(response); // JSON 응답
//    }


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