package com.example.tra.Services;

import com.example.tra.Entities.Applicant;
import com.example.tra.Entities.Interview;
import com.example.tra.Repositories.ApplicantRepository;
import com.example.tra.Repositories.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ApplicantService {

    @Autowired
    ApplicantRepository applicantRepository;

    @Autowired
    InterviewRepository interviewRepository;

    //Save full Applicant Object
    public Applicant saveApplicant(Applicant applicant) {
        if (applicant.getPassportNumber() == null || applicant.getPassportNumber().isEmpty()) {
            throw Exceptions.badRequest("Error:Passport number is required");
        }
        if (applicant.getFirstName() == null || applicant.getFirstName().isEmpty()) {
            throw Exceptions.badRequest("Error:first name is required");
        }
        if (applicant.getLastName() == null || applicant.getLastName().isEmpty()) {
            throw Exceptions.badRequest("Error:Last name is required");
        }
        return applicantRepository.save(applicant);
    }

    //Save From individual Strings
    public Applicant saveApplicant(String firstName, String lastName, String passportNumber, String nationality) {

        if (passportNumber == null || passportNumber.isEmpty()){
            throw Exceptions.badRequest("Error:Passport number is required");
        }
        if (firstName == null || lastName.isEmpty()){
            throw Exceptions.badRequest("Error:First name is required");
        }
        if (lastName == null || lastName.isEmpty()){
            throw Exceptions.badRequest("Error:Last Name is required");
        }

        Applicant applicant = new Applicant();
        applicant.setFirstName(firstName);
        applicant.setLastName(lastName);
        applicant.setPassportNumber(passportNumber);
        applicant.setNationality(nationality);
        return applicantRepository.save(applicant);
    }

    public Applicant flagCriminalRecord(Long applicantId){
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> Exceptions.notFound("Applicant not found with id: " + applicantId));

        applicant.setCriminalRecorde(true);
        applicantRepository.save(applicant);

        List<Interview> scheduledInterviews = interviewRepository.findByApplicantIdAndStatus(applicantId, "SCHEDULED");
        if (!scheduledInterviews.isEmpty()) {
            for (Interview interview : scheduledInterviews) {
                interview.setStatus("CANCELLED");
                interviewRepository.save(interview);
            }
        }
        return applicant;
    }

    public List<Applicant> getAllApplicant(){
        return applicantRepository.findAll();
    }


    public List<Applicant> findByNationality(String nationality){
        if (nationality == null || nationality.isEmpty()){
            throw Exceptions.badRequest("Error:Nationality is required");
        }
        return applicantRepository.findByNationality(nationality);
    }

}