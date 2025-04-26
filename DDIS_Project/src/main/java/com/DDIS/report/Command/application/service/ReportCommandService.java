package com.DDIS.report.Command.application.service;

import com.DDIS.report.Command.application.dto.ReportRegistRequest;

public interface ReportCommandService {

    Long registerReport(ReportRegistRequest reportRegistRequest);
    void handleReport(Long reportNum, Boolean accept);
}
