package com.DDIS.approve.Command.domain.aggregate.Entity;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Members;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_approves")
@IdClass(MemberApproveId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberApprove {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approve_num")
    private Approve approve;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Members member;

    @Column(name = "approve_status")
    private boolean approveStatus;  // true = 승인, false = 거절
}
