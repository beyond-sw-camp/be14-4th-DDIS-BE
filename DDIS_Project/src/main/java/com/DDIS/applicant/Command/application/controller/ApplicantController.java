package com.DDIS.applicant.Command.application.controller;



import com.DDIS.applicant.Command.application.service.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applicants")
@RequiredArgsConstructor
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping("/{postNum}")
    public ResponseEntity<Void> applyToPost(@PathVariable Long postNum,
                                            @RequestParam Long clientNum) {
        applicantService.applyToPost(postNum, clientNum);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postNum}")
    public ResponseEntity<Void> cancelApplication(@PathVariable Long postNum,
                                                  @RequestParam Long clientNum) {
        applicantService.cancelApplication(postNum, clientNum);
        return ResponseEntity.ok().build();
    }
}
