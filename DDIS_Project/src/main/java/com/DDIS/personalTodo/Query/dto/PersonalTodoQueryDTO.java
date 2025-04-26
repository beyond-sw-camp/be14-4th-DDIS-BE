package com.DDIS.personalTodo.Query.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Alias("PersonalTodoQueryDTO")
public class PersonalTodoQueryDTO {

    private Long todoNum;
    private String todoContent;
    private Long personalCategoryNum;
    private Long clientNum;

    // 조인해서 가져오는 값들
    private String personalCategoryName; // 카테고리 이름
    private String personalCategoryColorRgb; // 카테고리 색상
    private String todoDate; // 할 일 날짜
    private Boolean isDone; // 완료 여부
    private Integer pinOrder; // 핀 순서
    private Boolean isPublic; // 공개여부
}
