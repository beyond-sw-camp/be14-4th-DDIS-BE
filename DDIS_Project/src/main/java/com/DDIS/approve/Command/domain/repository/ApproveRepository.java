package com.DDIS.approve.Command.domain.repository;

import com.DDIS.approve.Command.domain.aggregate.Entity.Approve;
import com.DDIS.approve.Command.domain.aggregate.Entity.ApproveId;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApproveRepository extends JpaRepository<Approve, ApproveId> {

}
