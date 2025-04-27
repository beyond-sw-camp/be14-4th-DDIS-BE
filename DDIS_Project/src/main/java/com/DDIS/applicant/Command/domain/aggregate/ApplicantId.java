package com.DDIS.applicant.Command.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ApplicantId implements Serializable {
    private Long postNum;
    private Long clientNum;
}