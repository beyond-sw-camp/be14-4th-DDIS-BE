package com.DDIS.report.Command.application.controller;

import com.DDIS.report.Command.application.dto.ReportRegistRequest;
import com.DDIS.report.Command.application.service.ReportCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportCommandController {

    private final ReportCommandService reportCommandService;

    @PostMapping
    public ResponseEntity<Long> registerReport(@RequestBody ReportRegistRequest request) {
        Long id = reportCommandService.registerReport(request);
        return ResponseEntity.ok(id);
    }

    @PatchMapping("/{reportNum}/approve")
    public ResponseEntity<Void> approveReport(@PathVariable Long reportNum) {
        reportCommandService.handleReport(reportNum, true);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{reportNum}/reject")
    public ResponseEntity<Void> rejectReport(@PathVariable Long reportNum) {
        reportCommandService.handleReport(reportNum, false);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "I'm alive! 배포 테스트";
    }
}
