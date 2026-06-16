package com.example.tra.Services;

import com.example.tra.Entities.Applicant;
import com.example.tra.Entities.ImmigrationOfficer;
import com.example.tra.Entities.Interview;
import com.example.tra.Repositories.ApplicantRepository;
import com.example.tra.Repositories.InterviewRepository;
import com.example.tra.Repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {
    @Autowired
    InterviewRepository interviewRepository;

    @Autowired
    ApplicantRepository applicantRepository;

    @Autowired
    OfficerRepository officerRepository;


    public Interview scheduleInterview(Long applicantId,
                                       Long officerId,
                                       String date) {

        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("Applicant Not Found"));

        ImmigrationOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> new RuntimeException("Officer Not Found"));

        List<Interview> existingInterviews =
                interviewRepository.findByOfficerIdAndInterviewDate(officerId, date);

        if (!existingInterviews.isEmpty()) {
            throw new RuntimeException("Officer is double-booked!");
        }

        Interview interview = new Interview();

        interview.setApplicant(applicant);
        interview.setOfficer(officer);
        interview.setInterviewDate(date);
        interview.setStatus("SCHEDULED");

        return interviewRepository.save(interview);
    }

    // Complete Interview
    public Interview completeInterview(Long interviewId) {

        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview Not Found"));

        interview.setStatus("COMPLETED");

        return interviewRepository.save(interview);
    }
}
}
