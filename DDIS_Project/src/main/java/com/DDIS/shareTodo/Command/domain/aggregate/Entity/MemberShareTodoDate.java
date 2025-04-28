package com.DDIS.shareTodo.Command.domain.aggregate.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.*;

@Entity
@IdClass(MemberShareTodoDateId.class) // 복합키 있을 경우
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberShareTodoDate {

    @Id
    private String todoDate; // "2025-05-01"

    @Id
    private Long memberShareTodoNum;

    private boolean isDone;
    private boolean isPublic;
    private int pinOrder;
}
