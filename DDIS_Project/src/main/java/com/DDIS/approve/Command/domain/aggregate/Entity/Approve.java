package com.DDIS.approve.Command.domain.aggregate.Entity;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.members;
import jakarta.persistence.*;
import lombok.*;

@Entity

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "approves")
public class Approve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approve_num")
    private Long approveNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_share_todo_num")
    private MemberShareTodo memberShareTodoNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private members memberNum;

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
