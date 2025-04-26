package com.DDIS.report.Command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_num")
    private Long reportNum;

    @Column(name = "report_content", nullable = false)
    private String reportContent;

    @Column(name = "report_time")
    private String reportTime;

    @Column(name = "report_status", nullable = false)
    private Boolean reportStatus = false;

    @Column(name = "report_type", nullable = false)
    private String reportType;

    @Column(name = "client_num", nullable = false)
    private Long clientNum;

    @Column(name = "report_type_num", nullable = false)
    private Long reportTypeNum;

    @Column(name = "is_accepted")
    private Boolean isAccepted;

}
