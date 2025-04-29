package com.DDIS.shareTodo.Command.domain.repository;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberShareTodoRepository extends JpaRepository<MemberShareTodo, Long> {
    List<MemberShareTodo> findByMemberNum_ClientNum(Integer clientNum);

    List<MemberShareTodo> findByShareTodo_ShareTodoNum(Long shareTodoNum);
}
