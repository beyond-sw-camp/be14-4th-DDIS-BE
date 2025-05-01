package com.DDIS.postCategory.Query.controller;

import com.DDIS.postCategory.Query.dto.PostCategoryDTO;
import com.DDIS.postCategory.Query.service.PostCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController("postCategoryQueryController")
@RequestMapping("/categories")
@RequiredArgsConstructor
public class PostCategoryController {

    private final PostCategoryService categoryService;

    @GetMapping("")
    public List<PostCategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
