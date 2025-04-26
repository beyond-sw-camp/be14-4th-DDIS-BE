package com.DDIS.shareTodo.Command.domain.aggregate.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "member_share_todos")
public class MemberShareTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_share_todo_num")
    private int memberShareTodoNum;

    @ManyToOne
    @JoinColumn(name = "share_todo_num")
    private Sharetodo shareTodoNum;
    @ManyToOne
    @JoinColumn(name = "member_num")
    private members memberNum;

    @Column(name = "is_completed")
    private int isCompleted;

    @Column(name = "completion_time")
    private String completionTime;
}
