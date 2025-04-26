package com.DDIS.post.Command.domain.repository;

import com.DDIS.post.Command.domain.aggregate.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    // is_public = false 게시글 조회 메서드
    Optional<Post> findByPostNumAndIsPublicFalse(Long postNum);
}
