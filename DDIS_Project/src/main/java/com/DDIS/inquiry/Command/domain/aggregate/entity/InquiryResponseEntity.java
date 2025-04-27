package com.DDIS.inquiry.Command.domain.aggregate.entity;

import com.DDIS.inquiry.Command.application.dto.InquiryResponseResponseDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inquiry_responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InquiryResponseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_num")
    private Long responseNum;

    @Column(name = "inquiry_num", nullable = false)
    private Long inquiryNum;

    @Column(name = "response_content", length = 255)
    private String responseContent;

    @Column(name = "response_time", length = 255)
    private String responseTime;
}