package com.DDIS.shareTodo.Command.domain.repository;

import com.DDIS.shareTodo.Command.application.dto.DailyShareTodoDTO;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDate;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
            "LEFT JOIN MemberApprove ma ON a.approveNum = ma.approve.approveNum AND ma.member.memberNum = m.memberNum " +
            "WHERE m.room.roomNum = :roomNum AND m.client.clientNum = :clientNum AND d.todoDate = :todoDate " +
            "AND (a.approveNum IS NULL OR ma.approve IS NULL)")

    List<DailyShareTodoDTO> findDailyTodos(
            @Param("roomNum") Long roomNum,
            @Param("clientNum") Long clientNum,
            @Param("todoDate") String todoDate);

    List<MemberShareTodoDate> findByTodoDateAndMemberShareTodoNum(String dateStr, Long memberShareTodoNum);


    @Query(value = """
SELECT m.todo_date,
         m.member_share_todo_num,
         m.is_done,
         m.is_public,
         m.pin_order FROM member_share_todo_date m
JOIN member_share_todos mst ON m.member_share_todo_num = mst.member_share_todo_num
JOIN share_todos st ON mst.share_todo_num = st.share_todo_num
JOIN rooms r ON st.room_num = r.room_num
WHERE m.todo_date = :todoDate
AND r.room_num = :roomNum
AND m.is_done = 1
""", nativeQuery = true)
    List<MemberShareTodoDate> findDoneTodos(@Param("todoDate") String todoDate, @Param("roomNum") Long roomNum);


}
