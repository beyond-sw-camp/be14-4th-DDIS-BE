package com.DDIS.client.Command.domain.repository;

import com.DDIS.client.Command.domain.aggregate.ClientRoleEntity;
import com.DDIS.client.Command.domain.aggregate.ClientRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRoleRepository extends JpaRepository<ClientRoleEntity, ClientRoleId> {}
