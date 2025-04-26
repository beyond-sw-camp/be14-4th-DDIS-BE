//package com.DDIS.shareTodo.Command.domain.aggregate.Entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@ToString
//@Table(name = "share_todos")
//public class ShareTodo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "share_todo_num")
//    private Long shareTodoNum;
//
//    @Column(name = "share_todo_name")
//    private String shareTodoName;
//
//    @ManyToOne
//    @JoinColumn(name = "post_num")
//    private Posts post;
//
//    @Column(name = "pin_order")
//    private int pinOrder;
//
//}
