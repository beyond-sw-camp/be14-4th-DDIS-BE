package com.DDIS.shareTodo.Command.domain.aggregate.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@IdClass(MemberShareTodoDateId.class) // 복합키 있을 경우
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "member_share_todo_date")
public class MemberShareTodoDate {

    @Id
    @Column(name = "todo_date")
    private String todoDate; // "2025-05-01"

    @Id
    @Column(name = "member_share_todo_num")
    private Long memberShareTodoNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_share_todo_num", insertable = false, updatable = false)
    private MemberShareTodo memberShareTodo;

    @Column(name = "is_done")
    private boolean isDone;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "pin_order")
    private int pinOrder;
}
