package com.DDIS.report.Command.application.service;

import com.DDIS.client.Command.domain.aggregate.ClientEntity;
import com.DDIS.client.Command.domain.repository.ClientRepository;
import com.DDIS.post.Command.domain.aggregate.entity.Client;
import com.DDIS.post.Command.domain.aggregate.entity.Post;
import com.DDIS.post.Command.domain.repository.PostRepository;
import com.DDIS.report.Command.application.dto.ReportRegistRequest;
import com.DDIS.report.Command.domain.aggregate.entity.ReportEntity;
import com.DDIS.report.Command.domain.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportCommandServiceImpl implements ReportCommandService {

    private final ReportRepository reportRepository;
    private final ClientRepository clientRepository;
    private final PostRepository postRepository;
//    private final CommentRepository commentRepository;

    @Override
    public Long registerReport(ReportRegistRequest request) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ReportEntity reportEntity = ReportEntity.builder()
                .reportContent(request.getReportContent())
                .reportTime(now)
                .reportStatus(false)
                .reportType(request.getReportType())
                .clientNum(request.getClientNum())
                .reportTypeNum(request.getReportTypeNum())
                .isAccepted(null)
                .build();
        reportRepository.save(reportEntity);
        return reportEntity.getReportNum();
    }

    @Override
    public void handleReport(Long reportNum, Boolean accept) {
        ReportEntity report = reportRepository.findById(reportNum)
                .orElseThrow(() -> new RuntimeException("신고 없음"));
        if (report.getReportStatus()) {
            throw new IllegalStateException("이미 처리된 신고입니다.");
        }
        report.setIsAccepted(accept);
        report.setReportStatus(true);
        reportRepository.save(report);

        // 승인일 때 누적 처리
        if (accept) {
            Long targetClientNum = null;
            switch (report.getReportType()) {
                case "client":
                    targetClientNum = report.getReportTypeNum();
                    break;
                case "post":
                    Post post = postRepository.findById(report.getReportTypeNum())
                            .orElseThrow(() -> new RuntimeException("게시글 없음"));
                    targetClientNum = post.getClientNum().getClientNum();
                    break;
//                case "comment":
//                    Comment comment = commentRepository.findById(report.getReportTypeNum())
//                            .orElseThrow(() -> new RuntimeException("댓글 없음"));
//                    targetClientNum = comment.getClientNum();
//                    break;
            }
            if (targetClientNum != null) {
                ClientEntity client = clientRepository.findById(targetClientNum)
                        .orElseThrow(() -> new RuntimeException("회원 없음"));
                int count = client.getClientReportNum() + 1;
                client.setClientReportNum(count);
                if (count >= 3) {
                    client.setClientAccountStatus("DEACTIVATE");
                }
                clientRepository.save(client);
            }
        }
    }

}
