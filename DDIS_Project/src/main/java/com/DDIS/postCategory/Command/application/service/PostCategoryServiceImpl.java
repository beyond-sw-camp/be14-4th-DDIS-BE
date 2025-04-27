package com.DDIS.postCategory.Command.application.service;

import com.DDIS.postCategory.Command.domain.aggregate.entity.PostCategoryEntity;
import com.DDIS.postCategory.Command.domain.repository.PostCategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PostCategoryServiceImpl implements PostCategoryService {

        private final PostCategoryRepository categoryRepository;

        @Override
        public void createCategory(String categoryName) {
            PostCategoryEntity category = PostCategoryEntity.builder()
                    .categoryName(categoryName)
                    .build();
            categoryRepository.save(category);
        }

        @Override
        public void updateCategory(Long categoryNum, String categoryName) {
            PostCategoryEntity category = categoryRepository.findById(categoryNum)
                    .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));
            category.updateCategoryName(categoryName);
        }

        @Override
        public void deleteCategory(Long categoryNum) {
            categoryRepository.deleteById(categoryNum);
        }

}
