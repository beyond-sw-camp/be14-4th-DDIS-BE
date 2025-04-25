package com.DDIS.approve.Command.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateApproveDTO {
    private Long memberShareTodoNum;
    private Long memberNum;
    private String approveTitle;
    private String approveContent;
    private String approveTime;
}
