package com.DDIS.postCategory.Query.mapper;

import com.DDIS.postCategory.Query.dto.PostCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostCategoryMapper {
    List<PostCategoryDTO> findAllCategories();
}
