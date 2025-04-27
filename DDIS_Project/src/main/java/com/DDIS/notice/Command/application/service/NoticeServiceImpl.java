package com.DDIS.notice.Command.application.service;

import com.DDIS.chatRoom.Command.domain.aggregate.entity.ChatRoomEntity;
import com.DDIS.notice.Command.application.dto.NoticeRequestDTO;
import com.DDIS.notice.Command.application.dto.NoticeResponseDTO;
import com.DDIS.notice.Command.application.dto.NoticeUpdateDTO;
import com.DDIS.notice.Command.domain.aggregate.entity.NoticeEntity;
import com.DDIS.notice.Command.domain.repository.NoticeRepository;
import com.DDIS.shareTodo.Command.domain.aggregate.Entity.Rooms;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 공지사항 등록
    @Override
    public NoticeResponseDTO createNotice(NoticeRequestDTO requestDTO) {
        String now = LocalDateTime.now().format(FORMATTER);

        NoticeEntity notice = new NoticeEntity();
        notice.setNoticeTitle(requestDTO.getNoticeTitle());
        notice.setNoticeContent(requestDTO.getNoticeContent());
        notice.setNoticeTime(now);
        return NoticeResponseDTO.fromEntity(notice);
    }

    @Override
    public void updateNotice(Long noticeNum, NoticeUpdateDTO dto) {
        NoticeEntity post = noticeRepository.findById(noticeNum)
                .orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 존재하지 않습니다."));

        post.setNoticeTitle(dto.getTitle());
        post.setNoticeContent(dto.getContent());
        post.setNoticeTime(String.valueOf(LocalDateTime.now()));

        noticeRepository.save(post);
    }

    // 공지사항 삭제
    @Override
    public void deleteNotice(Long noticeNum) {
        noticeRepository.deleteById(noticeNum);
    }
}
