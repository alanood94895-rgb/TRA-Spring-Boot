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

    public Interview scheduleInterview(Long applicantId, Long officerId, String date) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> Exceptions.notFound("Applicant not found with id: " + applicantId));

        ImmigrationOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> Exceptions.notFound("Officer not found with id: " + officerId));


        List<Interview> existing = interviewRepository.findByOfficerIdAndInterviewDate(officerId, date);
        if (!existing.isEmpty()) {
            throw Exceptions.badRequest("Officer is double-booked!");
        }

        Interview interview = new Interview();
        interview.setApplicant(applicant);
        interview.setOfficer(officer);
        interview.setInterviewDate(date);
        interview.setStatus("SCHEDULED");

        return interviewRepository.save(interview);
    }

    public Interview completeInterview(Long interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> Exceptions.notFound("Interview not found with id: " + interviewId));

        interview.setStatus("COMPLETED");
        return interviewRepository.save(interview);
    }

    public Interview cancelInterview(Long interviewId){
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> Exceptions.notFound("Interview not found with id: " + interviewId));

        interview.setStatus("CANCELLED");
        return interviewRepository.save(interview);
    }

    public List<Interview> getOfficerSchedule(Long officerId, String date){
        return interviewRepository.findByOfficerIdAndInterviewDate(officerId,date);
    }
}