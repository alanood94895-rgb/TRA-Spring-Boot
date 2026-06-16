package com.example.tra.Controllers;

import com.example.tra.Entities.VisaApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/visas")
public class VisaController {
    @Autowired
    private VisaApplicationService visaService;

    @PostMapping("/submit/{applicantId}")
    public VisaApplication submitVisa(
            @PathVariable Long applicantId,
            @RequestParam String type) {

        return visaService.submitApplication(applicantId, type);
    }

    @PutMapping("/{visaId}/assign/{officerId}")
    public VisaApplication assignOfficer(
            @PathVariable Long visaId,
            @PathVariable Long officerId) {

        return visaService.assignOfficer(visaId, officerId);
    }

    @PutMapping("/{visaId}/process")
    public VisaApplication processVisa(
            @PathVariable Long visaId,
            @RequestParam String status,
            @RequestParam String notes) {

        return visaService.processVisa(visaId, status, notes);
    }

    @GetMapping("/applicant/{applicantId}")
    public List<VisaApplication> getByApplicant(@PathVariable Long applicantId) {
        return visaService.getByApplicant(applicantId);
    }

    @GetMapping("/status/{status}")
    public List<VisaApplication> getByStatus(@PathVariable String status) {
        return visaService.getByStatus(status);
    }
}

