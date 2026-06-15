package com.example.tra.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNumber;
    private String nationality;
    private Boolean criminalRecord;

    @OneToMany
    private List<VisaApplication> visaApplications;

    @OneToMany
    private List<Interview> interviews;
}
