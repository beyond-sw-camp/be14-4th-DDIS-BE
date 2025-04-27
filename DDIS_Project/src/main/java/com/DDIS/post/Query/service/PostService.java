package com.DDIS.post.Query.service;

import com.DDIS.post.Query.dto.PostDTO;
import com.DDIS.post.Query.dto.PublicPostDTO;

import java.util.List;

public interface PostService {

    // 1. 모든 모집게시글 조회 (관리자 조회 시)
    List<PostDTO> findAllPost();

    // 2. 모집게시글 전체공개 조회 (public)
    List<PublicPostDTO> findPublicPost();

    //



}
