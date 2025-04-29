package com.DDIS.shareTodo.Command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenerateTodoDatesRequest {
    private Long shareTodoNum;
    private List<Integer> selectedWeekdays; // 1=월 ~ 7=일
    private Integer year; // 연도 추가
    private Integer month; // 월 추가
}
