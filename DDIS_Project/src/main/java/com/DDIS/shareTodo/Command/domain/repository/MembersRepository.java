package com.DDIS.shareTodo.Command.domain.repository;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<Members, Integer> {
    Optional<Members> findByRoomNumAndClientNum(Long chatRoomNum, Long sender);
}
