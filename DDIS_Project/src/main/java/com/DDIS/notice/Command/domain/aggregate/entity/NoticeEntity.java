package com.DDIS.notice.Command.domain.aggregate.entity;

import com.DDIS.notice.Command.application.dto.NoticeRequestDTO;
import com.DDIS.notice.Command.application.dto.NoticeResponseDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_num")
    private Long noticeNum;

    @Column(name = "notice_title", nullable = false, length = 255)
    private String noticeTitle;

    @Column(name = "notice_content", nullable = false, length = 255)
    private String noticeContent;

    @Column(name = "notice_time", nullable = false, length = 255)
    private String noticeTime;

    @Column(name = "client_num", nullable = false)
    private Long clientNum;

    // Entity -> DTO 변환 메서드
    public NoticeResponseDTO toDTO() {
        return NoticeResponseDTO.builder()
                .noticeNum(this.noticeNum)
                .noticeTitle(this.noticeTitle)
                .noticeContent(this.noticeContent)
                .noticeTime(this.noticeTime)
                .clientNum(this.clientNum)
                .build();
    }
}
