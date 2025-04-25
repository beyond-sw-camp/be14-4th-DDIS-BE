package com.DDIS.report.Query.controller;

import com.DDIS.report.Query.dto.ReportDTO;
import com.DDIS.report.Query.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportQueryController {

    private final ReportService reportService;

    @Autowired
    public ReportQueryController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public List<ReportDTO> selectAllReports() {
        return reportService.selectAllReports();
    }
}
