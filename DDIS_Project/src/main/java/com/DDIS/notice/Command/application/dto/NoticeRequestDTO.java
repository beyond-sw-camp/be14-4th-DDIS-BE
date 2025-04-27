package com.DDIS.notice.Command.application.dto;

import com.DDIS.notice.Command.domain.aggregate.entity.NoticeEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NoticeRequestDTO {
    private String noticeTitle;
    private String noticeContent;
    private String noticeTime;
}