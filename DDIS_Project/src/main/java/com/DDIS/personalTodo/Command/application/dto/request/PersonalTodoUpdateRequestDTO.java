package com.DDIS.personalTodo.Command.application.dto.request;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
public class PersonalTodoUpdateRequestDTO {

    @NotNull(message = "todoNum은 필수입니다.")
    private Long todoNum;

    @NotBlank
    private String existingTodoDate; // 기존 날짜

    private String newTodoDate;      // 새로 수정할 날짜 (선택)

    private String todoContent;


    private Boolean isPublic;

    private Boolean isDone;

    private Boolean pinOrderUpdate; // true(핀 고정) / false(핀 해제)
}
