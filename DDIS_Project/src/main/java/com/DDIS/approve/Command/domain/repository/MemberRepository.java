package com.DDIS.approve.Command.domain.repository;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Members, Long> {
    List<Members> findByRoom_RoomNum(Long roomNum);

    List<Members> findByClient_ClientNum(Long clientNum);
}
