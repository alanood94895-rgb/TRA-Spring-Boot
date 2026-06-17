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

    public VisaApplication submitApplication(Long applicantId, String visaType){
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> Exceptions.notFound("Applicant not found with id: " + applicantId));

        VisaApplication visa = new VisaApplication();
        visa.setApplicant(applicant);
        visa.setVisaType(visaType);

        if (applicant.isCriminalRecorde()){
            visa.setStatus("REJECTED");
            visa.setVisaType("Auto-rejected due to criminal flag");
        }else {
            visa.setStatus("PENDING");
        }
        return visaApplicationRepository.save(visa);
    }

    public VisaApplication assignOfficer(Long visaId, Long officerId){
        VisaApplication visa = visaApplicationRepository.findById(visaId)
                .orElseThrow(() -> Exceptions.notFound("Visa application not found with id: " + visaId));

        ImmigrationOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> Exceptions.notFound("Officer not found with id: " + officerId));

        if (visa.getVisaType().equals("Asylum")){
            if (officer.getClearanceLevel() != 4 && officer.getClearanceLevel() != 5){
                throw Exceptions.badRequest("Asylum visas require officer with clearance level 4 or 5");
            }
        }
        visa.setHandlingOfficer(officer);
        return visaApplicationRepository.save(visa);
    }

    public VisaApplication processVisa(Long visaId, String newStatus, String notes){
        VisaApplication visa = visaApplicationRepository.findById(visaId)
                .orElseThrow(() -> Exceptions.notFound("Visa application not found with id: " + visaId));

        if (!newStatus.equals("APPROVED") && !newStatus.equals("REJECTED")){
            throw Exceptions.badRequest("Status must be APPROVED or REJECTED");
        }
        visa.setStatus(newStatus);
        visa.setOfficerNotes(notes);
        return visaApplicationRepository.save(visa);
    }

    public List<VisaApplication> getVisaByApplicant(Long applicantId){
        return visaApplicationRepository.findByApplicantId(applicantId);
    }

    public List<VisaApplication> gitVisaByStatus(String status){
        return visaApplicationRepository.findByStatus(status);
    }
}