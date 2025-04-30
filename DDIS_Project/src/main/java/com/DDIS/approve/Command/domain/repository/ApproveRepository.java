package com.DDIS.approve.Command.domain.repository;

import com.DDIS.approve.Command.domain.aggregate.Entity.Approve;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDate;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Members;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApproveRepository extends JpaRepository<Approve,Long> {


    boolean existsByMemberShareTodoNumAndMemberShareTodoDate(MemberShareTodo memberShareTodo, MemberShareTodoDate todoDateEntity);

    List<Approve> findByRoomNum(Long roomNum);
    @Query("SELECT a FROM Approve a WHERE a.memberShareTodoNum.memberShareTodoNum = :memberShareTodoNum")
    Optional<Approve> findByMemberShareTodoNum(@Param("memberShareTodoNum") Long memberShareTodoNum);



    @Query("""
    SELECT a 
    FROM Approve a 
    WHERE a.approveNum NOT IN (
        SELECT ma.approve.approveNum 
        FROM MemberApprove ma 
        WHERE ma.member.memberNum = :memberNum
    )
    AND a.roomNum = :roomNum
""")
    List<Approve> findUnvotedApprovesByMember(@Param("memberNum") Long memberNum, @Param("roomNum") Long roomNum);


}
