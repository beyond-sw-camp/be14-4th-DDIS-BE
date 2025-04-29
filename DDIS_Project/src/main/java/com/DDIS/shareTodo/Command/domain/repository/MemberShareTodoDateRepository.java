package com.DDIS.shareTodo.Command.domain.repository;

import com.DDIS.shareTodo.Command.application.dto.DailyShareTodoDTO;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDate;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberShareTodoDateRepository extends JpaRepository<MemberShareTodoDate, MemberShareTodoDateId> {

    @Query("SELECT new com.DDIS.shareTodo.Command.application.dto.DailyShareTodoDTO(" +
            "d.memberShareTodoNum, d.todoDate, d.isDone, d.isPublic, d.pinOrder, " +
            "t.shareTodoNum, t.shareTodoName, " +
            "a.approveNum, a.approveTitle, a.approveContent, a.approveTime, a.approvePermitCount, a.approveRefuseCount) " +
            "FROM MemberShareTodoDate d " +
            "JOIN MemberShareTodo mst ON d.memberShareTodoNum = mst.memberShareTodoNum " +
            "JOIN ShareTodo t ON mst.shareTodo.shareTodoNum = t.shareTodoNum " +
            "JOIN Members m ON mst.memberNum.memberNum = m.memberNum " +
            "LEFT JOIN Approve a ON a.memberShareTodoNum.memberShareTodoNum = d.memberShareTodoNum AND a.todoDate = d.todoDate " +
            "WHERE m.room.roomNum = :roomNum AND m.client.clientNum = :clientNum AND d.todoDate = :todoDate")
    List<DailyShareTodoDTO> findDailyTodos(
            @Param("roomNum") Long roomNum,
            @Param("clientNum") Long clientNum,
            @Param("todoDate") String todoDate);
}
