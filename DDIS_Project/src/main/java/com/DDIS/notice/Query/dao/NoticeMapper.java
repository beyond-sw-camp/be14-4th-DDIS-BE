package com.DDIS.notice.Query.dao;

import com.DDIS.notice.Query.dto.NoticeQueryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeQueryDTO> selectAllNotices();

    NoticeQueryDTO selectNoticeByNum(Long noticeNum);
}
