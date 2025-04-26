package com.DDIS.personalCategory.Query.mapper;

import com.DDIS.personalCategory.Query.dto.PersonalCategoryQueryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonalCategoryMapper {

    List<PersonalCategoryQueryDTO> findCategoriesByClientNum(@Param("clientNum") Long clientNum);
}
