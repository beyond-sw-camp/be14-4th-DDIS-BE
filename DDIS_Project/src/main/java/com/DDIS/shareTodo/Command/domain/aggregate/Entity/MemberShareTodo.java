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
    private Long memberShareTodoNum;

    @ManyToOne
    @JoinColumn(name = "share_todo_num")
    private ShareTodo shareTodoNum;

    @ManyToOne
    @JoinColumn(name = "member_num")
    private Members memberNum;
}
