package com.DDIS.personalTodo.Command.application.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalTodoCreateRequestDTO {

    @NotBlank(message = "할 일 내용은 필수입니다.")
    private String todoContent;

    @NotNull(message = "생성 타입은 필수입니다.")
    private CreateType createType;

    private String todoDate;
    private List<String> todoDates;
    private RepeatInfo repeatInfo;

    private Long personalCategoryNum;
    private Boolean isPublic = false;
    private Integer pinOrder = 0;
}

