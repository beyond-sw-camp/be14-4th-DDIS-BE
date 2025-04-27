package com.DDIS.postCategory.Command.domain.service;

public interface PostCategoryService {


    // 1. 모집게시글 카테고리 생성
    void createCategory(String categoryName);

    // 2. 모집게시글 카테고리 수정
    void updateCategory(Long categoryNum, String categoryName);

    // 3. 모집게시글 카테고리 삭제
    void deleteCategory(Long categoryNum);
}
