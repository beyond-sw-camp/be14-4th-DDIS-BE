// 핸들러 역할
package com.DDIS.post.Query.controller;

import com.DDIS.post.Query.dto.AdminPostDTO;
import com.DDIS.post.Query.dto.PublicPostDTO;
import com.DDIS.post.Query.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 1. 모집게시글 전체 조회 (관리자)
    @GetMapping("/findAll")
    public ResponseEntity<List<AdminPostDTO>> getAllPosts() { List<AdminPostDTO> posts = postService.findAllPost();

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 상태 코드 반환
        }

        return ResponseEntity.ok(posts); // 200 Introduce local variable
    }

    // 2. public 모집게시글 조회
    @GetMapping("/public")
    public ResponseEntity<List<PublicPostDTO>> getPublicPosts() {
        List<PublicPostDTO> posts = postService.findPublicPost();

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(posts); // 200 OK
    }

    // 3. 카테고리별 모집게시글 조회
    @GetMapping("/category/{categoryNum}")
    public ResponseEntity<List<PublicPostDTO>> getPostsByCategory(@PathVariable Long categoryNum) {
        List<PublicPostDTO> posts = postService.findPostsByCategory(categoryNum);

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 No Content
        }

        return ResponseEntity.ok(posts);  // 200 OK + 데이터
    }

    // 4. 최신 모집일 기준 정렬 조회
    @GetMapping("/latest")
    public ResponseEntity<List<PublicPostDTO>> getPostsOrderByStartDateDesc() {
        List<PublicPostDTO> posts = postService.findPostsOrderByStartDateDesc();

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(posts); // 200 OK
    }

}
