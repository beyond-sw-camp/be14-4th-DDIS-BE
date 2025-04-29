package com.DDIS.shareTodo.Command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class DailyShareTodoDTO {

    private Long memberShareTodoNum;
    private String todoDate;
    private boolean isDone;
    private boolean isPublic;
    private int pinOrder;

    private Long shareTodoNum;
    private String shareTodoName;

    private Long approveNum;
    private String approveTitle;
    private String approveContent;
    private String approveTime;
    private Integer approvePermitCount;
    private Integer approveRefuseCount;

    // 순서 주의! → JPQL SELECT 절과 일치해야 함
    public DailyShareTodoDTO(
            Long memberShareTodoNum,
            String todoDate,
            boolean isDone,
            boolean isPublic,
            int pinOrder,
            Long shareTodoNum,
            String shareTodoName,
            Long approveNum,
            String approveTitle,
            String approveContent,
            String approveTime,
            Integer approvePermitCount,
            Integer approveRefuseCount
    ) {
        this.memberShareTodoNum = memberShareTodoNum;
        this.todoDate = todoDate;
        this.isDone = isDone;
        this.isPublic = isPublic;
        this.pinOrder = pinOrder;
        this.shareTodoNum = shareTodoNum;
        this.shareTodoName = shareTodoName;
        this.approveNum = approveNum;
        this.approveTitle = approveTitle;
        this.approveContent = approveContent;
        this.approveTime = approveTime;
        this.approvePermitCount = approvePermitCount;
        this.approveRefuseCount = approveRefuseCount;
    }
}
