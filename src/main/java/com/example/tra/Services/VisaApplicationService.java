package com.example.tra.Services;

import com.example.tra.Entities.Applicant;
import com.example.tra.Entities.ImmigrationCenter;
import com.example.tra.Entities.ImmigrationOfficer;
import com.example.tra.Entities.VisaApplication;
import com.example.tra.Repositories.ApplicantRepository;
import com.example.tra.Repositories.CenterRepository;
import com.example.tra.Repositories.OfficerRepository;
import com.example.tra.Repositories.VisaApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisaApplicationService {

    @Autowired
    VisaApplicationRepository visaApplicationRepository;

    @Autowired
    ApplicantRepository applicantRepository;

    @Autowired
    OfficerRepository officerRepository;

    public VisaApplication submitApplication(Long applicantId,
                                             String visaType) {

        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant Not Found"));

        VisaApplication visa = new VisaApplication();

        visa.setApplicant(applicant);
        visa.setVisaType(visaType);

        if (Boolean.TRUE.equals(applicant.getCriminalRecord())) {
            visa.setStatus("REJECTED");
            visa.setOfficerNotes("Auto-rejected due to criminal flag.");
        } else {
            visa.setStatus("PENDING");
        }

        return visaApplicationRepository.save(visa);
    }

    public VisaApplication assignOfficer(Long visaId, Long officerId) {

        VisaApplication visa = visaApplicationRepository.findById(visaId)
                .orElseThrow(() -> new RuntimeException("Visa Not Found"));

        ImmigrationOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> new RuntimeException("Officer Not Found"));

        if (visa.getVisaType().equalsIgnoreCase("Asylum")) {

            if (officer.getClearanceLevel() < 4) {
                throw new RuntimeException(
                        "Asylum applications require clearance level 4 or 5");
            }
        }

        visa.setHandlingOfficer(officer);

        return visaApplicationRepository.save(visa);
    }

    public VisaApplication processVisa(Long visaId, String newStatus, String notes) {

        VisaApplication visa = visaApplicationRepository.findById(visaId)
                .orElseThrow(() -> new RuntimeException("Visa Not Found"));

        if (!newStatus.equalsIgnoreCase("APPROVED") &&
                !newStatus.equalsIgnoreCase("REJECTED")) {

            throw new RuntimeException("Status must be APPROVED or REJECTED");
        }

        visa.setStatus(newStatus);
        visa.setOfficerNotes(notes);

        return visaApplicationRepository.save(visa);
    }
    public List<VisaApplication> getByApplicant(Long applicantId) {
        return visaApplicationRepository.findAll()
                .stream()
                .filter(v -> v.getApplicant().getId().equals(applicantId))
                .toList();
    }

    public List<VisaApplication> getByStatus(String status) {
        return visaApplicationRepository.findAll()
                .stream()
                .filter(v -> v.getStatus().equalsIgnoreCase(status))
                .toList();
    }
}