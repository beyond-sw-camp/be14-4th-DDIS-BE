package com.DDIS.approve.Command.domain.repository;

import com.DDIS.approve.Command.domain.aggregate.Entity.MemberApprove;
import com.DDIS.approve.Command.domain.aggregate.Entity.MemberApproveId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberApproveRepository extends JpaRepository<MemberApprove, MemberApproveId> {

    boolean existsByApprove_ApproveNumAndMember_MemberNum(Long approveNum, Long memberNum);

    @Query("SELECT ma.approve.approveNum FROM MemberApprove ma WHERE ma.member.memberNum = :memberNum")
    List<Long> findApproveIdsByMemberNum(@Param("memberNum")Long memberNum);
}