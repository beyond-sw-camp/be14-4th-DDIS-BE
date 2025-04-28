package com.DDIS.approve.Command.application.controller;

import com.DDIS.approve.Command.application.dto.ApproveResponseDTO;
import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
import com.DDIS.approve.Command.application.dto.UpdateApproveStatusDTO;
import com.DDIS.approve.Command.application.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApproveController {

     private ApproveService approveService;


    @Autowired
    public ApproveController( ApproveService approveService) {
        this.approveService = approveService;
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


}
