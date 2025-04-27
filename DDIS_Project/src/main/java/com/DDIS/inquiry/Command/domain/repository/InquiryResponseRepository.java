package com.DDIS.inquiry.Command.domain.repository;

import com.DDIS.inquiry.Command.domain.aggregate.entity.InquiryResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryResponseRepository extends JpaRepository<InquiryResponseEntity, Long> {
}
