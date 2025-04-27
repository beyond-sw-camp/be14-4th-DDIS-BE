package com.DDIS.notice.Query.service;

import com.DDIS.notice.Command.application.dto.NoticeResponseDTO;
import com.DDIS.notice.Command.domain.aggregate.entity.NoticeEntity;
import com.DDIS.notice.Query.dao.NoticeMapper;
import com.DDIS.notice.Query.dto.NoticeQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeQueryService {

    private final NoticeMapper noticeMapper;

    @Autowired
    public NoticeQueryService(NoticeMapper noticePostMapper) {
        this.noticeMapper = noticePostMapper;
    }

    public List<NoticeQueryDTO> getAllNotices() {
        return noticeMapper.selectAllNotices();
    }

    public NoticeQueryDTO getNoticeDetail(Long noticeNum) {
        return noticeMapper.selectNoticeByNum(noticeNum);
    }
}
