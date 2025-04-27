// Java에서 SQL을 호출할 인터페이스 정의

package com.DDIS.post.Query.mapper;

import com.DDIS.post.Query.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    // 1. 전체 게시글 조회 (public, private -> 관리자 조회시)
    List<PostDTO> findAllPosts();

    // 2. 회원번호(작성자)별 조회

    // 3. 카테고리별 조회

    // 4. 등록일 최신순 조회


}