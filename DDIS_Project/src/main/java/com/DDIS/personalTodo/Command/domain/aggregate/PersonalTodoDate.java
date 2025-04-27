package com.DDIS.personalTodo.Command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "personal_todo_date")
@IdClass(PersonalTodoDateId.class) // 복합키 관리용
public class PersonalTodoDate {

    @Id
    private String todoDate; // YYYY-MM-DD 같은 형식

    @Id
    private Long todoNum;

    @Column(nullable = false)
    private boolean isDone = false;

    @Column(nullable = false)
    private boolean isPublic = false;

    private Integer pinOrder = 0;

}