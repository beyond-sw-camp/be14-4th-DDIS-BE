package com.DDIS.approve.Command.domain.repository;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberShareTodoRepository extends JpaRepository<MemberShareTodo, Long> {
}
