package com.DDIS.approve.Command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateApproveDTO {
    private String approve_title;
    private String approve_content;
    private String time;
    private int permitCount;
}
