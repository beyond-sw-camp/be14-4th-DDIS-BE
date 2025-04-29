package com.DDIS.shareTodo.Command.domain.aggregate.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberShareTodoDateId implements Serializable {
    private String todoDate;
    private Long memberShareTodoNum;

    // equals()와 hashCode() 꼭 재정의해야 함
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberShareTodoDateId)) return false;
        MemberShareTodoDateId that = (MemberShareTodoDateId) o;
        return Objects.equals(todoDate, that.todoDate) &&
                Objects.equals(memberShareTodoNum, that.memberShareTodoNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todoDate, memberShareTodoNum);
    }
}
