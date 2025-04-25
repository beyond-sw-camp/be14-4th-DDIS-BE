package com.DDIS.approve.Command.application.controller;

import com.DDIS.approve.Command.application.dto.CreateApproveDTO;
import com.DDIS.approve.Command.application.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApproveController {
     private Environment env;
     private ApproveService approveService;


    @Autowired
    public ApproveController(Environment env, ApproveService approveService) {
        this.env = env;
        this.approveService = approveService;
    }

    @GetMapping
    public ResponseEntity<?> createApprove(@RequestBody CreateApproveDTO approveDTO) {
        approveService.createApprove(approveDTO);

    }
}
