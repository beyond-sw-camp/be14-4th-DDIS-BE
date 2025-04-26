// 메서드 시그니처만 작성
package com.DDIS.post.Command.application.service;

import com.DDIS.post.Command.domain.aggregate.dto.PostCreateRequestDTO;
import com.DDIS.post.Command.domain.aggregate.entity.Post;

public interface PostService {


    // 1. 전체 모집게시글 조회 (비공개 비밀번호 검증 포함)
    Post getPost(Long postNum, String inputPassword);

    // 2. 모집게시글 작성
    void createPost(PostCreateRequestDTO request);

}
