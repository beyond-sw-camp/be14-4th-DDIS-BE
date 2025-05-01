package com.DDIS.client.Command.domain.repository;

import com.DDIS.client.Command.domain.aggregate.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<UserEntity, Long> {

    // Optional로 반환하는 것이므로, 값이 존재하지 않으면 빈 Optional을 반환
    Optional<UserEntity> findByClientId(String clientId);

    Optional<UserEntity> findByClientEmail(String clientEmail);

    Optional<UserEntity> findByClientNameAndClientEmail(String clientName, String clientEmail);

    // 리스트 형태로 반환할 필요 없음, 이미 clientId나 clientNum으로 찾는 메서드가 있음
    Optional<UserEntity> findByClientNum(Long clientNum);
}
