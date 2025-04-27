package com.DDIS.inquiry.Command.domain.repository;

import com.DDIS.inquiry.Command.domain.aggregate.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
}