package com.DDIS.shareTodo.Command.domain.repository;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDate;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberShareTodoDateRepository extends JpaRepository<MemberShareTodoDate, MemberShareTodoDateId> {

}
