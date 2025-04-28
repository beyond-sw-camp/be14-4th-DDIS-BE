package com.DDIS.post.Command.domain.repository;

import com.DDIS.post.Command.domain.aggregate.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 제목이나 내용에 키워드가 포함된 게시글 검색
    List<Post> findByPostTitleContainingOrPostContentContaining(String titleKeyword, String contentKeyword);
}

