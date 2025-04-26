package com.DDIS.shareTodo.Command.application.dto;


import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Posts;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShareRoomDTO {
    private Long postNum;
    private int memberCount;
    private boolean isActive;
    private String title;
    private String content;
    private String colorRgb;
    private int approveRequiredCount;
    private String startDate;
    private String endDate;
}
