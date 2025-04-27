package com.DDIS.notice.Command.application.dto;

import com.DDIS.notice.Command.domain.aggregate.entity.NoticeEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NoticeResponseDTO {

    private Long noticeNum;
    private String noticeTitle;
    private String noticeContent;
    private String noticeTime;
    private Long clientNum;

    // Entity -> DTO 변환
    public static NoticeResponseDTO fromEntity(NoticeEntity entity) {
        return NoticeResponseDTO.builder()
                .noticeNum(entity.getNoticeNum())
                .noticeTitle(entity.getNoticeTitle())
                .noticeContent(entity.getNoticeContent())
                .noticeTime(entity.getNoticeTime())
                .clientNum(entity.getClientNum())
                .build();
    }
}