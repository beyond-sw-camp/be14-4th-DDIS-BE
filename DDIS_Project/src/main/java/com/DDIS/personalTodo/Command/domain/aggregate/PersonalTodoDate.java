package com.DDIS.personalTodo.Command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "personal_todo_date")
public class PersonalTodoDate {

    @EmbeddedId
    private PersonalTodoDateId id;

    @Column(nullable = false)
    private boolean isDone = false;

    @Column(nullable = false)
    private boolean isPublic = false;

    private Integer pinOrder = 0;

    @Builder
    public PersonalTodoDate(String todoDate, Long todoNum, boolean isDone, boolean isPublic, Integer pinOrder) {
        this.id = new PersonalTodoDateId(todoDate, todoNum); // EmbeddedId를 내부에서 생성
        this.isDone = isDone;
        this.isPublic = isPublic;
        this.pinOrder = pinOrder;
    }

    public void updateIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void updateIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public void updatePinOrder(Integer pinOrder) {
        this.pinOrder = pinOrder;
    }
}
