package com.DDIS.approve.Command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateApproveStatusDTO {
    private Long approveNum;
    private Long memberNum;
    private String action;
}
