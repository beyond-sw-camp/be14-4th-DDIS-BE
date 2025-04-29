package com.DDIS.approve.Command.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateApproveDTO {
    private Long memberShareTodoNum;
    private Long memberNum;
    private Long roomNum;
    private String todoDate;
    private String approveTitle;
    private String approveContent;
}
