// 구현체 실제 로직 작성
package com.DDIS.post.Command.application.service;

import com.DDIS.client.Command.domain.aggregate.UserEntity;
import com.DDIS.client.Command.domain.repository.ClientRepository;
import com.DDIS.post.Command.domain.aggregate.dto.PostCreateRequestDTO;
import com.DDIS.post.Command.domain.aggregate.entity.Post;
import com.DDIS.post.Command.domain.mapper.PostCommandMapper;
import com.DDIS.post.Command.domain.repository.PostRepository;
import com.DDIS.postCategory.Command.domain.aggregate.entity.PostCategoryEntity;
import com.DDIS.postCategory.Command.domain.repository.PostCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("commandPostServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ClientRepository clientRepository;
    private final PostCategoryRepository categoryRepository;
    private final PostCommandMapper postCommandMapper;

    @Override
    public Post getPost(Long postNum, String inputPassword) {
        Post post = postRepository.findById(postNum)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (Boolean.FALSE.equals(post.getIsPublic())) {
            if (inputPassword == null) {
                throw new IllegalArgumentException("비밀번호를 입력해주세요.");
            }

            String savedPassword = post.getPostPassword();
            if (savedPassword == null || !savedPassword.trim().equals(inputPassword.trim())) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }
        }
        return post;
    }

    @Override
    public void createPost(PostCreateRequestDTO dto) {
        PostCategoryEntity category = categoryRepository.findById(dto.getCategoryNum())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        UserEntity client = clientRepository.findById(dto.getClientNum())
                .orElseThrow(() -> new IllegalArgumentException("작성자를 찾을 수 없습니다."));

        Post post = postCommandMapper.toEntity(dto, category, client);
        postRepository.save(post);
    }





}
