package com.DDIS.approve.Command.application.dto;

import lombok.Getter;

@Getter
public class UpdateApproveStatusDTO {
    private Long approveNum;
    private Long memberShareTodoNum;
    private Long memberNum;
    private String action;
}
