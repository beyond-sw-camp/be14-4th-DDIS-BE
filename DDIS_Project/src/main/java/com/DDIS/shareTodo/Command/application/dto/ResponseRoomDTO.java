package com.DDIS.shareTodo.Command.application.dto;

import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseRoomDTO {
    private Long roomNum;
    private String title;
    private String content;
    private String colorRgb;
    private String startDate;
    private String endDate;
    private int memberCount;
    private int approveRequiredCount;

    public static ResponseRoomDTO from(Rooms room) {
        return new ResponseRoomDTO(
                room.getRoomNum(),
                room.getTitle(),
                room.getContent(),
                room.getColorRgb(),
                room.getStartDate(),
                room.getEndDate(),
                room.getMemberCount(),
                room.getApproveRequiredCount()
        );
    }
}
