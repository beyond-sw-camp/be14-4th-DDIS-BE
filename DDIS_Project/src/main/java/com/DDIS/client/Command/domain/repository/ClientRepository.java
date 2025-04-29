package com.DDIS.client.Command.domain.repository;

import com.DDIS.client.Command.domain.aggregate.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByClientId(String clientId);

    Optional<UserEntity> findByClientEmail(String email);

    List<UserEntity> clientId(String clientId);
}
