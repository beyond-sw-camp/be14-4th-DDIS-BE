package com.DDIS.personalCategory.Query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Alias("PersonalCategoryQueryDTO")
public class PersonalCategoryQueryDTO {
    private Long personalCategoryNum;
    private String personalCategoryName;
}
