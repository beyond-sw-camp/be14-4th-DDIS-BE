package com.DDIS.notice.Query.controller;

import com.DDIS.notice.Query.dto.NoticeQueryDTO;
import com.DDIS.notice.Query.service.NoticeQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notices")
public class NoticeQueryController {

    private final NoticeQueryService noticeQueryService;

    @Autowired
    public NoticeQueryController(NoticeQueryService noticeQueryService) {
        this.noticeQueryService = noticeQueryService;
    }

    @GetMapping
    public ResponseEntity<List<NoticeQueryDTO>> getAllNotices() {
        List<NoticeQueryDTO> list = noticeQueryService.getAllNotices();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{noticeCode}")
    public ResponseEntity<NoticeQueryDTO> getNoticeByNoticeCode(@PathVariable("noticeCode") Long noticeNum) {
        NoticeQueryDTO result= noticeQueryService.getNoticeDetail(noticeNum);
        return ResponseEntity.ok(result);
    }
}
