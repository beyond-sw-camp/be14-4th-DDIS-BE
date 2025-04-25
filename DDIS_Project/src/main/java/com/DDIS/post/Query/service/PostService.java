package com.DDIS.post.Query.service;

import com.DDIS.post.Query.dto.PostDTO;
import java.util.List;

public interface PostService {

    // 모집게시글 전체조회
    List<PostDTO> findAllPost();
}
