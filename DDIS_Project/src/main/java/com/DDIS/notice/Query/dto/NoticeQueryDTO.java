package com.DDIS.notice.Query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeQueryDTO {
    private Long noticeNum;
    private String noticeTitle;
    private String noticeContent;
    private String noticeTime;
    private Long clientNum;
}
