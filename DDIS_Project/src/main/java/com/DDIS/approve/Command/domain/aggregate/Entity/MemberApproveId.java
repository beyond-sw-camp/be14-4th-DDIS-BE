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
public class MemberApproveId implements Serializable {
    private Long approve;
    private Long member;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberApproveId)) return false;
        MemberApproveId that = (MemberApproveId) o;
        return Objects.equals(approve, that.approve) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(approve, member);
    }
}
