package com.DDIS.client.Command.domain.repository;

import com.DDIS.client.Command.domain.aggregate.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {}