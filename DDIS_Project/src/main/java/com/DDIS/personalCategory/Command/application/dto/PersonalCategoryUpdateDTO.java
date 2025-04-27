package com.DDIS.personalCategory.Command.application.dto;

import com.DDIS.personalCategory.Command.domain.aggregate.PersonalCategoryColor;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonalCategoryUpdateDTO {

    @NotBlank(message = "카테고리 이름은 필수입니다.")
    private String name;

    private PersonalCategoryColor color; // enum 이름 (예: RED, ORANGE 등)

    public String getTrimmedName() {
        return name != null ? name.trim() : null;
    }
}
