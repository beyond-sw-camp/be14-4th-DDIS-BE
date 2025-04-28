package com.DDIS.post.Query.service;

import com.DDIS.post.Query.dto.AdminPostDTO;
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
    public List<AdminPostDTO> findAllPost() { return postMapper.findAllPosts();}

    // 2. 전체 공개 모집게시글 조회
    @Override
    public List<PublicPostDTO> findPublicPost() { return postMapper.findPublicPosts();}

    // 3. 카테고리별 모집게시글 조회
    @Override
    public List<PublicPostDTO> findPostsByCategory(Long categoryNum) { return postMapper.findPostsByCategory(categoryNum);}

    // 4. 최신 모집일 기준 정렬 조회
    @Override
    public List<PublicPostDTO> findPostsOrderByStartDateDesc() { return postMapper.findPostsOrderByStartDateDesc();}


}
