package com.DDIS.approve.Command.domain.aggregate.Entity;

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
public class ApproveId implements Serializable {

    private Long approveNum;
    private Long memberShareTodoNum;
    private Long memberNum;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApproveId)) return false;
        ApproveId that = (ApproveId) o;
        return Objects.equals(approveNum, that.approveNum)
                && Objects.equals(memberShareTodoNum, that.memberShareTodoNum)
                && Objects.equals(memberNum, that.memberNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(approveNum, memberShareTodoNum, memberNum);
    }
}



