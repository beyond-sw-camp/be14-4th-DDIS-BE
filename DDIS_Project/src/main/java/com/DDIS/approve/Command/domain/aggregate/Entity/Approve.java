package com.DDIS.approve.Command.domain.aggregate.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(ApproveId.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "approves")
public class Approve {

    @Id
    @Column(name = "approve_num")
    private Long approveNum;

    @Id
    @Column(name = "member_share_todo_num")
    private Long memberShareTodoNum;

    @Id
    @Column(name = "member_num")
    private Long memberNum;

    @Column(name = "approve_title")
    private String approveTitle;

    @Column(name = "approve_content")
    private String approveContent;

    @Column(name = "approve_time")
    private String approveTime;

    @Column(name = "approve_permit_count")
    private int approvePermitCount;

    @Column(name = "approve_refuse_count")
    private int approveRefuseCount;


}
