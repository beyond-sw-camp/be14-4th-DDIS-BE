// 구현체 실제 로직 작성
package com.DDIS.post.Command.application.service;

import com.DDIS.post.Command.domain.aggregate.entity.Post;
import com.DDIS.post.Command.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("commandPostServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

        private final PostRepository postRepository;

    @Override
    public Post getPost(Long postNum, String inputPassword) {
        Post post = postRepository.findById(postNum)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (Boolean.FALSE.equals(post.getIsPublic())) {
            // 비공개 게시글이면 비밀번호 검증
            if (!post.getPostPassword().equals(inputPassword)) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        }
        // 공개 게시글이면 그냥 통과
        return post;
    }
}
