package com.DDIS.shareTodo.Command.domain.aggregate.Entity;

import com.DDIS.post.Command.domain.aggregate.entity.Post;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "share_todos")
public class ShareTodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "share_todo_num")
    private Long shareTodoNum;

    @Column(name = "share_todo_name")
    private String shareTodoName;

    @ManyToOne
    @JoinColumn(name = "post_num")
    private Post post;

    @Column(name = "pin_order")
    private int pinOrder;

}
