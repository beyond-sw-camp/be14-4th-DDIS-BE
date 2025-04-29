package com.DDIS.post.Query.service;

import com.DDIS.post.Query.dto.AdminPostDTO;
import com.DDIS.post.Query.dto.PostCloseResponseDTO;
import com.DDIS.post.Query.dto.PublicPostDTO;

import java.util.List;

public interface PostService {

    // 1. 모든 모집게시글 조회 (관리자 조회 시)
    List<AdminPostDTO> findAllPost();

    // 2. 모집게시글 전체공개 조회 (public)
    List<PublicPostDTO> findPublicPost();

    // 3. 카테고리별 조회 (public)
    List<PublicPostDTO> findPostsByCategory(Long categoryNum);

    // 4. 최신 모집일 기준 정렬 조회
    List<PublicPostDTO> findPostsOrderByStartDateDesc();

    // 5. 방장 공동방 생성을 위한 조회
    PostCloseResponseDTO checkAndClosePost(Long postNum, Long requesterClientNum);

}
