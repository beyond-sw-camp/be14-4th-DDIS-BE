package com.DDIS.personalCategory.Query.service;

import com.DDIS.personalCategory.Query.dto.PersonalCategoryQueryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonalCategoryQueryService {

    List<PersonalCategoryQueryDTO> getCategoriesByClientNum(Long clientNum);
}
