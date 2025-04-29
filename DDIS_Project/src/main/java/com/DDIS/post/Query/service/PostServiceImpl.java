package com.DDIS.post.Query.service;

import com.DDIS.post.Query.dto.AdminPostDTO;
import com.DDIS.post.Query.dto.PostCloseResponseDTO;
import com.DDIS.post.Query.dto.PostCreateTodoRoomDTO;
import com.DDIS.post.Query.dto.PublicPostDTO;
import com.DDIS.post.Query.mapper.PostMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    // 5. 공동 방 생성을 위한 조회
    @Override
    @Transactional
    public PostCloseResponseDTO checkAndClosePost(Long postNum, Long requesterClientNum) {
        PostCreateTodoRoomDTO post = postMapper.findPostById(postNum);

        if (post == null) {
            throw new RuntimeException("게시글이 존재하지 않습니다.");
        }

        // 본인이 쓴 글인지 확인
        if (!post.getClientNum().equals(requesterClientNum)) {
            throw new RuntimeException("본인이 작성한 글만 조회할 수 있습니다.");
        }

        boolean shouldClose = false;

        // 모집 마감일 체크
        LocalDate today = LocalDate.now();
        LocalDate endDate = LocalDate.parse(post.getRecruitmentEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (today.isAfter(endDate)) {
            shouldClose = true;
        }

        // 모집 인원 체크
        if (post.getApplicantCount() != null &&
                post.getApplicantCount() >= post.getRecruitmentLimit()) {
            shouldClose = true;
        }

        // 조건 충족하고, 아직 닫히지 않은 경우
        if (shouldClose && !post.getIsClosed()) {
            postMapper.closePost(postNum);
            return new PostCloseResponseDTO(postNum, true);
        } else {
            return new PostCloseResponseDTO(postNum, post.getIsClosed());
        }

    }

}
