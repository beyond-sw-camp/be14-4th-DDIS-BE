// Java에서 SQL을 호출할 인터페이스 정의

package com.DDIS.post.Query.mapper;

import com.DDIS.post.Query.dto.AdminPostDTO;
import com.DDIS.post.Query.dto.PostCreateTodoRoomDTO;
import com.DDIS.post.Query.dto.PublicPostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    // 1. 전체 모집게시글 조회 (public, private -> 관리자 조회시)
    List<AdminPostDTO> findAllPosts();

    // 2. 전체공개 모집게시글 조회
    List<PublicPostDTO> findPublicPosts();

    // 3. 카테고리별 조회
    List<PublicPostDTO> findPostsByCategory(@Param("categoryNum") Long categoryNum);

    // 4. 등록일 최신순 조회
    List<PublicPostDTO> findPostsOrderByStartDateDesc();

    // 5. 작성자 공동방 생성
    PostCreateTodoRoomDTO findPostById(Long postNum);
    void closePost(Long postNum);

}