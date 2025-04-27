package com.DDIS.post.Query.service;

import com.DDIS.post.Query.dto.PostDTO;
import com.DDIS.post.Query.dto.PublicPostDTO;
import com.DDIS.post.Query.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// 비즈니스 로직 처리
@Service
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    // 1. 모든 모집게시글 조회
    @Override
    public List<PostDTO> findAllPost() {
        return postMapper.findAllPosts();
    }

    // 2. 전체 공개 모집게시글 조회
    @Override
    public List<PublicPostDTO> findPublicPost() { return postMapper.findPublicPosts();}
}
