package com.example.tra.Entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsylumSeeker extends Applicant {

    private String countryOfOrigin;
    private String sponsorOrganization;
}
