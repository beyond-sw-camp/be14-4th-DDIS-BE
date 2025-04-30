package com.DDIS.approve.Command.domain.aggregate.Entity;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodo;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.MemberShareTodoDate;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Members;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "approves")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Approve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approve_num")
    private Long approveNum;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num")
    private Members memberNum;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_share_todo_num")
    private MemberShareTodo memberShareTodoNum;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "todo_date", referencedColumnName = "todo_date", insertable = false, updatable = false),
            @JoinColumn(name = "member_share_todo_num", referencedColumnName = "member_share_todo_num", insertable = false, updatable = false)

    })
    private MemberShareTodoDate memberShareTodoDate;

    @Column(name = "room_num")
    private Long roomNum;

    @Column(name = "todo_date", nullable = false)
    private String todoDate;

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

