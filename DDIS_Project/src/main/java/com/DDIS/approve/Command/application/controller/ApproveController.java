package com.DDIS.approve.Command.application.controller;

import com.DDIS.approve.Command.application.dto.ApproveResponseDTO;
import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
import com.DDIS.approve.Command.application.dto.UpdateApproveStatusDTO;
import com.DDIS.approve.Command.application.service.ApproveService;
import com.DDIS.approve.Command.domain.repository.MemberApproveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApproveController {

     private ApproveService approveService;
     private MemberApproveRepository memberApproveRepository;


    @Autowired
    public ApproveController( ApproveService approveService, MemberApproveRepository memberApproveRepository) {
        this.approveService = approveService;
        this.memberApproveRepository = memberApproveRepository;
    }


    @GetMapping("/approve")
    public  ResponseEntity<List<ApproveResponseDTO>> getAllApproves() {
        List<ApproveResponseDTO> approves = approveService.getAllApproves();
        return ResponseEntity.ok(approves);
    }

    @GetMapping("/approve/{id}")
    public ResponseEntity<?> getApprove(@PathVariable Long id) {
        return ResponseEntity.ok(approveService.getApprove(id));
    }
    @PostMapping("/approve/create")
    public ResponseEntity<?> createApprove(@RequestBody CreateApproveDTO approveDTO) {
        approveService.createApprove(approveDTO);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/approve/status")
    public ResponseEntity<?> updateApproveStatus(@RequestBody UpdateApproveStatusDTO dto) {
        approveService.updateApproveStatus(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/approve/unvoted")
    public ResponseEntity<?> getUnvotedApproves(
            @RequestParam Long memberNum,
            @RequestParam Long roomNum
    ) {
        return ResponseEntity.ok(approveService.getUnvotedApproves(memberNum, roomNum));
    }

    @GetMapping("/approve/handled")
    public ResponseEntity<List<Long>> getHandledApproveIds(@RequestParam Long memberNum) {
        List<Long> handled = memberApproveRepository.findApproveIdsByMemberNum(memberNum);
        return ResponseEntity.ok(handled);
    }

}
