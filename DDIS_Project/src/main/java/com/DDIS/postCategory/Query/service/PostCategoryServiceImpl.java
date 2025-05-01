package com.DDIS.postCategory.Query.service;

import com.DDIS.postCategory.Query.dto.PostCategoryDTO;
import com.DDIS.postCategory.Query.mapper.PostCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("postCategoryQueryService")
@RequiredArgsConstructor
public class PostCategoryServiceImpl implements PostCategoryService{


    private final PostCategoryMapper postCategoryMapper;

    @Override
    public List<PostCategoryDTO> getAllCategories() {
        return postCategoryMapper.findAllCategories();
    }
}
