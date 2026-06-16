package com.example.tra.Services;

import com.example.tra.Entities.Applicant;
import com.example.tra.Entities.Interview;
import com.example.tra.Repositories.ApplicantRepository;
import com.example.tra.Repositories.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class ApplicantService {

    @Autowired
     ApplicantRepository applicantRepository;

    @Autowired
     InterviewRepository interviewRepository;

    public ApplicantService(ApplicantRepository applicantRepository,
                            InterviewRepository interviewRepository) {
        this.applicantRepository = applicantRepository;
        this.interviewRepository = interviewRepository;
    }

    public Applicant saveApplicant(Applicant applicant) {

        if (applicant.getPassportNumber() == null || applicant.getPassportNumber().isEmpty()) {
            throw new RuntimeException("Passport number cannot be null or empty");
        }

        if (applicant.getFirstName() == null || applicant.getFirstName().isEmpty()) {
            throw new RuntimeException("First name is required");
        }

        if (applicant.getLastName() == null || applicant.getLastName().isEmpty()) {
            throw new RuntimeException("Last name is required");
        }

        return applicantRepository.save(applicant);
    }


    public Applicant saveApplicant(String firstName,
                                   String lastName,
                                   String passportNumber,
                                   String nationality) {

        if (passportNumber == null || passportNumber.isEmpty()) {
            throw new RuntimeException("Passport number cannot be null or empty");
        }

        if (firstName == null || firstName.isEmpty()) {
            throw new RuntimeException("First name is required");
        }

        if (lastName == null || lastName.isEmpty()) {
            throw new RuntimeException("Last name is required");
        }

        Applicant applicant = new Applicant();
        applicant.setFirstName(firstName);
        applicant.setLastName(lastName);
        applicant.setPassportNumber(passportNumber);
        applicant.setNationality(nationality);

        return applicantRepository.save(applicant);
    }

    public void flagCriminalRecord(Long applicantId) {

        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant not found"));

        applicant.setCriminalRecord(true);
        applicantRepository.save(applicant);

        List<Interview> scheduledInterviews =
                interviewRepository.findByOfficerIdAndInterviewDate(applicantId, "SCHEDULED");

        for (Interview interview : scheduledInterviews) {
            interview.setStatus("CANCELLED");
        }

        interviewRepository.saveAll(scheduledInterviews);
    }
}


