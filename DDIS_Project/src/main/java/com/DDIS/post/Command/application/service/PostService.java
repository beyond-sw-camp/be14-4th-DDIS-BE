// 메서드 시그니처만 작성
package com.DDIS.post.Command.application.service;

import com.DDIS.post.Command.domain.aggregate.dto.PostCreateRequestDTO;
import com.DDIS.post.Command.domain.aggregate.dto.PostResearchDTO;
import com.DDIS.post.Command.domain.aggregate.dto.PostResponseDTO;
import com.DDIS.post.Command.domain.aggregate.dto.PostUpdateRequestDTO;
import com.DDIS.post.Command.domain.aggregate.entity.Post;

import java.util.List;

public interface PostService {


    // 1. 전체 모집게시글 조회 (비공개 비밀번호 검증 포함)
    Post getPost(Long postNum, String inputPassword);

    // 2. 모집게시글 작성
    void createPost(PostCreateRequestDTO request);

    // 3. 모집게시글 수정
    void updatePost(Long postNum, PostUpdateRequestDTO request, Long requesterId);

    // 4. 모집게시글 삭제
    void deletePost(Long postNum, Long requesterId);

    // 5. 모집게시글 검색
    List<PostResearchDTO> searchPosts(String keyword);
}
