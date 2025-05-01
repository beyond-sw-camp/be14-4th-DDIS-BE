package com.DDIS.postCategory.Query.service;

import com.DDIS.postCategory.Query.dto.PostCategoryDTO;
import java.util.List;


public interface PostCategoryService {

    List<PostCategoryDTO> getAllCategories();
}
