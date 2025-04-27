package com.DDIS.applicant.Command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "applicants")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ApplicantId.class) // 복합키 매핑
public class ApplicantEntity {

    @Id
    private Long postNum;

    @Id
    private Long clientNum;
}