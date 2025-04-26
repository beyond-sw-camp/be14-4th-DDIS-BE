package com.DDIS.personalCategory.Query.service;

import com.DDIS.personalCategory.Query.dto.PersonalCategoryQueryDTO;
import com.DDIS.personalCategory.Query.mapper.PersonalCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalCategoryQueryServiceImpl implements PersonalCategoryQueryService{

    private final PersonalCategoryMapper personalCategoryMapper;

    @Override
    public List<PersonalCategoryQueryDTO> getCategoriesByClientNum(Long clientNum) {
        return personalCategoryMapper.findCategoriesByClientNum(clientNum);
    }
}
