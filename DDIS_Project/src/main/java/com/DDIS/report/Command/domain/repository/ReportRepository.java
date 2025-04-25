package com.DDIS.report.Command.domain.repository;

import com.DDIS.report.Command.domain.aggregate.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}
