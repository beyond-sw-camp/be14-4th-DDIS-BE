package com.DDIS.personalCategory.Query.controller;

import com.DDIS.personalCategory.Query.dto.PersonalCategoryQueryDTO;
import com.DDIS.personalCategory.Query.service.PersonalCategoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("personalCategory")
@RequiredArgsConstructor
public class PersonalCategoryQueryController {

    private final PersonalCategoryQueryService personalCategoryQueryService;

    // 개인이 등록한 카테고리 목록 조회
    @GetMapping("/{clientNum}")
    public List<PersonalCategoryQueryDTO> getCategoriesByClientNum(@PathVariable Long clientNum) {
        return personalCategoryQueryService.getCategoriesByClientNum(clientNum);
    }
}
