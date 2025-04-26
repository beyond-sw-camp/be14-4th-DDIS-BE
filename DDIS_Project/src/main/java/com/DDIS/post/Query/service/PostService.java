package com.DDIS.post.Query.service;

import com.DDIS.post.Query.dto.PostDTO;
import java.util.List;

public interface PostService {

    // 모집게시글 전체조회
    List<PostDTO> findAllPost();

    // 모집게시글 전체공개 조회

    // 모집게시글 비공개 조회시 비밀번호..입력?로직



}
