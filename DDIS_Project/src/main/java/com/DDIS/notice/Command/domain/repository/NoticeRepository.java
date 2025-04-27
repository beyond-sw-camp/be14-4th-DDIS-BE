package com.DDIS.notice.Command.domain.repository;

import com.DDIS.notice.Command.domain.aggregate.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

}
