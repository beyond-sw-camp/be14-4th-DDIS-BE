package com.DDIS.client.Command.domain.repository;

import com.DDIS.post.Command.domain.aggregate.entity.Client;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientsRepository extends JpaRepository<Clients, Long> {
}
