// 구현체 실제 로직 작성
package com.DDIS.post.Command.application.service;

import com.DDIS.client.Command.domain.aggregate.UserEntity;
import com.DDIS.client.Command.domain.repository.ClientRepository;
import com.DDIS.post.Command.domain.aggregate.dto.PostCreateRequestDTO;
import com.DDIS.post.Command.domain.aggregate.dto.PostResearchDTO;
import com.DDIS.post.Command.domain.aggregate.dto.PostResponseDTO;
import com.DDIS.post.Command.domain.aggregate.dto.PostUpdateRequestDTO;
import com.DDIS.post.Command.domain.aggregate.entity.Post;
import com.DDIS.post.Command.domain.repository.PostRepository;
import com.DDIS.postCategory.Command.domain.aggregate.entity.PostCategoryEntity;
import com.DDIS.postCategory.Command.domain.repository.PostCategoryRepository;
//import com.DDIS.shareTodo.Command.domain.aggregate.entity.ShareTodo;
//import com.DDIS.shareTodo.Command.domain.repository.ShareTodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service("commandPostServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ClientRepository clientRepository;
    private final PostCategoryRepository categoryRepository;


    // 1. 모집 게시글 조회
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

    // 2. 모집게시글 작성
    @Override
    @Transactional
    public void createPost(PostCreateRequestDTO dto) {
        // 카테고리, 작성자 조회
        PostCategoryEntity category = categoryRepository.findById(dto.getCategoryNum())
                .orElseThrow(() -> new IllegalArgumentException("카테고리를 찾을 수 없습니다."));
        UserEntity client = clientRepository.findById(dto.getClientNum())
                .orElseThrow(() -> new IllegalArgumentException("작성자를 찾을 수 없습니다."));

        if (dto.getCategoryNum() == null) {
            throw new IllegalArgumentException("카테고리 ID는 필수입니다.");
        }
        if (dto.getClientNum() == null) {
            throw new IllegalArgumentException("작성자 ID는 필수입니다.");
        }

        // 날짜 입력값 유효성 검사
        if (dto.getStartDate() == null || dto.getEndDate() == null ||
                dto.getStartDate().isBlank() || dto.getEndDate().isBlank()) {
            throw new IllegalArgumentException("활동 시작일과 종료일은 필수입니다.");
        }

        // 현재 날짜
        String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String nowDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 모집게시글 저장
        Post post = Post.builder()
                .postTitle(dto.getPostTitle())
                .postContent(dto.getPostContent())
                .recruitmentStartDate(nowDate)
                .recruitmentEndDate(dto.getRecruitmentEndDate())
                .activityTime(7)
                .recruitmentLimit(dto.getRecruitmentLimit())
                .isPublic(dto.getIsPublic())
                .postPassword(dto.getPostPassword())
                .categoryNum(category)
                .clientNum(client)
                .applicantCount(0)
                .isClosed(false)
                .createdDate(nowDateTime)
                .build();
        postRepository.save(post);
    }


    @Override
    @Transactional
    public void updatePost(Long postNum, PostUpdateRequestDTO request, Long requesterId) {
        Post post = postRepository.findById(postNum)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // 모집 마감 여부 체크
        if (Boolean.TRUE.equals(post.getIsClosed())) {
            throw new RuntimeException("모집이 마감된 게시글은 수정할 수 없습니다.");
        }

        // 작성자 본인 확인 (추가 권한 검사)
        if (!post.getClientNum().getClientNum().equals(requesterId)) {
            throw new RuntimeException("작성자 본인만 수정할 수 있습니다.");
        }

        // 제목, 내용 수정 (updatedDate 포함됨)
        post.updatePost(
                request.getPostTitle(),
                request.getPostContent()
        );
    }

    // 4. 모집게시글 삭제
    @Override
    @Transactional
    public void deletePost(Long postNum, Long requesterId) {
        Post post = postRepository.findById(postNum)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        // TODO: 시큐리티 적용 후 작성자 확인 다시 추가
//    if (!post.getClientNum().getClientNum().equals(requesterId)) {
//        throw new RuntimeException("작성자만 삭제할 수 있습니다.");
//    }

        // ✅ 삭제 시간 (deleteDate) 세팅
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        post.softDelete(now);
    }


    // 5. 모집게시글 검색(제목, 내용)
    @Override
    public List<PostResearchDTO> searchPosts(String keyword) {
        List<Post> posts = postRepository.findByPostTitleContainingOrPostContentContaining(keyword, keyword);

        return posts.stream()
                .map(post -> new PostResearchDTO(
                        post.getPostNum(),
                        post.getPostTitle(),
                        post.getPostContent()
                ))
                .collect(Collectors.toList());
    }

}
