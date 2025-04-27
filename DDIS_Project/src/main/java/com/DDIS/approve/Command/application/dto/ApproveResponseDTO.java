package com.DDIS.approve.Command.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApproveResponseDTO {
    private Long approveId;
    private Long memberShareTodoNum;
    private Long memberNum;
    private String approveTitle;
    private String approveContent;
    private String approveTime;
}
