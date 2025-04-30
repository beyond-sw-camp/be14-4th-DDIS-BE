package com.DDIS.shareTodo.Command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoneLogResponse {
    private String nickname;
    private String message;
    private String doneTime;
}
