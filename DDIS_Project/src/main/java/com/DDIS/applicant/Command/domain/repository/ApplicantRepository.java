package com.DDIS.applicant.Command.domain.repository;

import com.DDIS.applicant.Command.domain.aggregate.ApplicantEntity;
import com.DDIS.applicant.Command.domain.aggregate.ApplicantId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<ApplicantEntity, ApplicantId> {

    boolean existsByPostNumAndClientNum(Long postNum, Long clientNum);

    List<ApplicantEntity> findByPostNum(Long postNum);
}
