// Java에서 SQL을 호출할 인터페이스 정의

package com.DDIS.post.Query.mapper;

import com.DDIS.post.Query.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {


    List<PostDTO> findAllPosts();
}