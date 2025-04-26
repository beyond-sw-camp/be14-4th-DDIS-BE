package com.DDIS.report.Command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportRegistRequest {

    private String reportContent;
    private String reportType;
    private Long clientNum;
    private Long reportTypeNum;

}
