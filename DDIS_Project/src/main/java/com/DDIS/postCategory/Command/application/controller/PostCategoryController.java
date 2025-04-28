package com.DDIS.postCategory.Command.application.controller;

import com.DDIS.postCategory.Command.application.dto.CategoryCreateRequestDTO;
import com.DDIS.postCategory.Command.application.dto.CategoryUpdateRequestDTO;
import com.DDIS.postCategory.Command.application.service.PostCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category") // 관리자 전용
@RequiredArgsConstructor
public class PostCategoryController {

    private final PostCategoryService categoryService;

    // 1. 모집게시글 카테고리 생성
    @PostMapping("/createCategory")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryCreateRequestDTO dto) {
        categoryService.createCategory(dto.getCategoryName());
        return ResponseEntity.ok().build();
    }

    // 2. 모집게시글 카테고리 수정
    @PatchMapping("/updateCategory/{categoryNum}")
    public ResponseEntity<Void> updateCategory(@PathVariable Long categoryNum,
                                               @RequestBody CategoryUpdateRequestDTO dto) {
        categoryService.updateCategory(categoryNum, dto.getCategoryName());
        return ResponseEntity.ok().build();
    }

    // 3. 모집게시글 카테고리 삭제
    @DeleteMapping("/deleteCategory/{categoryNum}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryNum) {
        categoryService.deleteCategory(categoryNum);
        return ResponseEntity.ok().build();
    }
}

