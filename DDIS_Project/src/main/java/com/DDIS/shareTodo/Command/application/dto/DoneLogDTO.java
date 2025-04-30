package com.DDIS.shareTodo.Command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoneLogDTO {
    private String nickname;
    private String message; // 예: "꿀잠 잤어요!"
    private String doneTime; // 예: "16:28:13"
}
