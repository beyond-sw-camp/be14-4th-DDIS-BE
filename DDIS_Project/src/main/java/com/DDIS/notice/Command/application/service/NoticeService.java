package com.DDIS.notice.Command.application.service;

import com.DDIS.notice.Command.application.dto.NoticeRequestDTO;
import com.DDIS.notice.Command.application.dto.NoticeResponseDTO;
import com.DDIS.notice.Command.application.dto.NoticeUpdateDTO;

public interface NoticeService {
    NoticeResponseDTO createNotice(NoticeRequestDTO requestDTO);

    void updateNotice(Long noticeNum, NoticeUpdateDTO dto);

    void deleteNotice(Long noticeNum);
}
