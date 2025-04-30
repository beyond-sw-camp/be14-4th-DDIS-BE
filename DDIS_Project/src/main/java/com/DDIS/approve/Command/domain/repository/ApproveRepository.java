package com.DDIS.approve.Command.domain.repository;

import com.DDIS.approve.Command.domain.aggregate.Entity.Approve;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDate;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApproveRepository extends JpaRepository<Approve,Long> {


    boolean existsByMemberShareTodoNumAndMemberShareTodoDate(MemberShareTodo memberShareTodo, MemberShareTodoDate todoDateEntity);

    List<Approve> findByRoomNum(Long roomNum);
}
