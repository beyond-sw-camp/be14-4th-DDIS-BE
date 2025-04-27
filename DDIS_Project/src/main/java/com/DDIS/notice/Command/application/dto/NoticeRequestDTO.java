package com.DDIS.notice.Command.application.dto;

import com.DDIS.inquiry.Command.domain.aggregate.entity.InquiryEntity;
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
//    private String noticeTime;
    private Long clientNum;

    public NoticeEntity toEntity() {
        return NoticeEntity.builder()
                .noticeTitle(this.noticeTitle)
                .noticeContent(this.noticeContent)
                .clientNum(this.clientNum)   // 여기 꼭 세팅돼야 함!!!
                .build();
    }
}