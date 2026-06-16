package com.example.tra.Controllers;

import com.example.tra.Entities.Applicant;
import com.example.tra.Repositories.ApplicantRepository;
import com.example.tra.Services.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {

    @Autowired
    private ApplicantRepository applicantRepository;

    @Autowired
    private ApplicantService applicantService;

    @PostMapping
    public Applicant registerApplicant(@RequestBody Applicant applicant) {
        return applicantRepository.save(applicant);
    }

    @PostMapping("/asylum")
    public AsylumSeeker registerAsylum(@RequestBody AsylumSeeker seeker) {
        return applicantRepository.save(seeker);
    }

    @GetMapping
    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    @GetMapping("/search")
    public List<Applicant> findByNationality(@RequestParam String nationality) {
        return applicantRepository.findByNationality(nationality);
    }

    @PutMapping("/{id}/flag")
    public Applicant flagCriminalRecord(@PathVariable Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        applicant.setCriminalRecord(true);
        return applicantRepository.save(applicant);
    }
}