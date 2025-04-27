// 핸들러 역할
package com.DDIS.post.Query.controller;

import com.DDIS.post.Query.dto.PostDTO;
import com.DDIS.post.Query.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 1. 전체 조회 요청 핸들러
    @GetMapping("/findAll")
    public ResponseEntity<List<PostDTO>> getAllPosts() { List<PostDTO> posts = postService.findAllPost();

        if (posts.isEmpty()) {
            return ResponseEntity.noContent().build();  // 204 상태 코드 반환
        }

        return ResponseEntity.ok(posts); // 200 Introduce local variable
    }


}
