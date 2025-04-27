package com.DDIS.notice.Command.application.controller;

import com.DDIS.notice.Command.application.dto.NoticeRequestDTO;
import com.DDIS.notice.Command.application.dto.NoticeUpdateDTO;
import com.DDIS.notice.Command.application.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNotice(@RequestBody NoticeRequestDTO dto) {
        noticeService.createNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("공지사항 등록 완료");
    }

    @PutMapping("/update/{noticeNum}")
    public ResponseEntity<String> updateNotice(@PathVariable Long noticeNum,
                                               @RequestBody NoticeUpdateDTO dto) {
        noticeService.updateNotice(noticeNum, dto);
        return ResponseEntity.ok("공지사항 수정 완료");
    }

    @DeleteMapping("/delete/{noticeNum}")
    public ResponseEntity<String> deleteNotice(@PathVariable Long noticeNum) {
        noticeService.deleteNotice(noticeNum);
        return ResponseEntity.ok("공지사항 삭제 완료");
    }
}
