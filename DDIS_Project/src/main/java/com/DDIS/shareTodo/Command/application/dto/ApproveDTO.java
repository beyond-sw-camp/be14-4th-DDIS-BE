package com.DDIS.shareTodo.Command.application.dto;

import com.DDIS.approve.Command.domain.aggregate.Entity.Approve;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApproveDTO {
    private Long id;
    private String title;
    private String content;
    private String time;
    private int permitCount;
    private int refuseCount;

    public ApproveDTO(Approve approve) {
        this.id = approve.getApproveNum(); // PK
        this.title = approve.getApproveTitle();
        this.content = approve.getApproveContent();
        this.time = approve.getApproveTime();
        this.permitCount = approve.getApprovePermitCount();
        this.refuseCount = approve.getApproveRefuseCount();
    }
}