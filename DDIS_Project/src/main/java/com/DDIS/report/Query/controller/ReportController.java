package com.DDIS.report.Query.controller;

import com.DDIS.report.Query.dto.ReportDTO;
import com.DDIS.report.Query.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public List<ReportDTO> selectAllReports() {
        return reportService.selectAllReports();
    }
}
