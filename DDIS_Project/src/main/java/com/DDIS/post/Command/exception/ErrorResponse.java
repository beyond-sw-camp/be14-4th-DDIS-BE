package com.DDIS.post.Command.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 에러 응답 형태 정의
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private int status;  // HTTP 상태코드
    private String message;  // 에러 메시지
}
