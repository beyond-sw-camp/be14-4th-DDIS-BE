package com.DDIS.report.Query.service;

import com.DDIS.report.Query.dto.ReportDTO;
import com.DDIS.report.Query.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private final ReportMapper reportMapper;

    @Autowired
    public ReportService(ReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    public List<ReportDTO> selectAllReports() {
        return reportMapper.selectAllReports();
    }
}
