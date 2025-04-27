package com.DDIS.inquiry.Command.domain.aggregate.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inquiries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class InquiryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_num")
    private Long inquiryNum;

    @Column(name = "inquiry_title", nullable = false, length = 255)
    private String inquiryTitle;

    @Column(name = "inquiry_content", nullable = false, length = 255)
    private String inquiryContent;

    @Column(name = "inquiry_time", nullable = false, length = 255)
    private String inquiryTime;

    @Column(name = "client_num", nullable = false)
    private Long clientNum;
}