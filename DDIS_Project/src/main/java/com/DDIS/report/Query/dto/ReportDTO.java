package com.DDIS.report.Query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ReportDTO {

    private Long reportNum;
    private String reportContent;
    private String reportTime;
    private Boolean reportStatus;
    private String reportType;
    private Long clientNum;
    private String clientNickname;
    private Long reportTypeNum;
    private Boolean isAccepted;

}
