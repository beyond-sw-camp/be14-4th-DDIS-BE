package com.DDIS.personalCategory.Command.application.controller;

import com.DDIS.personalCategory.Command.application.dto.PersonalCategoryCreateDTO;
import com.DDIS.personalCategory.Command.application.dto.PersonalCategoryUpdateDTO;
import com.DDIS.personalCategory.Command.application.service.PersonalCategoryCommandService;
import com.DDIS.personalCategory.Command.domain.aggregate.PersonalCategories;
import com.DDIS.personalCategory.Command.domain.repository.PersonalCategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal-categories")
@RequiredArgsConstructor
public class PersonalCategoryCommandController {

    private final PersonalCategoryCommandService personalCategoryCommandService;
    private final PersonalCategoryRepository personalCategoryRepository;

    /**
     * 카테고리 생성
     */
    @PostMapping
    public ResponseEntity<Void> createCategory(
            @RequestParam Long clientNum,
            @Valid @RequestBody PersonalCategoryCreateDTO requestDTO
    ) {
        Long categoryId = personalCategoryCommandService.createCategory(clientNum, requestDTO);
        return ResponseEntity.created(null).build();
    }

    /**
     * 카테고리 수정
     */
    @PatchMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(
            @RequestParam Long clientNum,
            @PathVariable Long categoryId,
            @Valid @RequestBody PersonalCategoryUpdateDTO requestDTO
    ) {
        // 1. 카테고리 조회
        PersonalCategories category = personalCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        // 2. 소유자 검증
        if (!category.getClientNum().equals(clientNum)) {
            throw new IllegalArgumentException("본인 소유의 카테고리만 수정할 수 있습니다.");
        }

        // 3. 수정 진행
        personalCategoryCommandService.updateCategory(clientNum, categoryId, requestDTO);

        return ResponseEntity.ok("카테고리 수정 완료");
    }

    /**
     * 카테고리 삭제
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(
            @RequestParam Long clientNum,
            @PathVariable Long categoryId,
            @RequestParam(name = "action", defaultValue = "detach") String action
    ) {
        // 1. 카테고리 조회
        PersonalCategories category = personalCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));

        // 2. 소유자 검증
        if (!category.getClientNum().equals(clientNum)) {
            throw new IllegalArgumentException("본인 소유의 카테고리만 삭제할 수 있습니다.");
        }

        // 3. 삭제 진행
        personalCategoryCommandService.deleteCategory(categoryId, action);

        return ResponseEntity.ok("카테고리 삭제 완료");
    }
}
