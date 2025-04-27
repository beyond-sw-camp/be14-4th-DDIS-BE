package com.DDIS.personalCategory.Command.application.service;

import com.DDIS.personalCategory.Command.application.dto.PersonalCategoryCreateDTO;
import com.DDIS.personalCategory.Command.application.dto.PersonalCategoryUpdateDTO;

public interface PersonalCategoryCommandService {

    // 1. 카테고리 생성
    Long createCategory(Long clientNum, PersonalCategoryCreateDTO request);

    // 2. 카테고리 수정
    void updateCategory(Long clientNum, Long categoryId, PersonalCategoryUpdateDTO request);

    // 3. 카테고리 삭제
    void deleteCategory(Long categoryId, String action);
}
