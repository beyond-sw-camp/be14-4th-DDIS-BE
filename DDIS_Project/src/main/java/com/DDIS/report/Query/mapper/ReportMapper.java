package com.DDIS.report.Query.mapper;

import com.DDIS.report.Query.dto.ReportDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReportMapper {
    List<ReportDTO> selectAllReports();
}
