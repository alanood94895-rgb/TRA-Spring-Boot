package com.example.tra.Repositories;

import com.example.tra.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository  extends JpaRepository<Interview, Long> {

    List<Interview> findByOfficerIdAndInterviewDate(
            Long officerId,
            String interviewDate);
}
