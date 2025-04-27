package com.DDIS.personalTodo.Command.domain.aggregate;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalTodoDateId implements Serializable {

    private String todoDate;
    private Long todoNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonalTodoDateId)) return false;
        PersonalTodoDateId that = (PersonalTodoDateId) o;
        return Objects.equals(todoDate, that.todoDate) && Objects.equals(todoNum, that.todoNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todoDate, todoNum);
    }
}
