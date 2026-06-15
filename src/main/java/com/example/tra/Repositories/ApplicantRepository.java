package com.example.tra.Repositories;

import com.example.tra.Entities.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

        List<Applicant> findByNationality(String nationality);
}
