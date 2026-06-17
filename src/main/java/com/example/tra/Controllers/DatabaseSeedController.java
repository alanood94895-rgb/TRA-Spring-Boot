package com.example.tra.Controllers;

import com.example.tra.Entities.Applicant;
import com.example.tra.Entities.BorderControlOfficer;
import com.example.tra.Entities.ImmigrationCenter;
import com.example.tra.Entities.ImmigrationOfficer;
import com.example.tra.Repositories.ApplicantRepository;
import com.example.tra.Repositories.CenterRepository;
import com.example.tra.Repositories.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseSeedController {

    @Autowired
    CenterRepository centerRepository;

    @Autowired
    OfficerRepository officerRepository;

    @Autowired
    ApplicantRepository applicantRepository;

    @PostMapping("/api/seed")
    public ResponseEntity<String> seedDatabase() {

        ImmigrationCenter center1 = new ImmigrationCenter();
        center1.setName("Muscat Embassy");
        center1.setLocationCountry("Oman");
        center1.setType("Embassy");
        center1.setDailyCapacity(50);
        centerRepository.save(center1);

        ImmigrationCenter center2 = new ImmigrationCenter();
        center2.setName("Muscat Border Control");
        center2.setLocationCountry("Oman");
        center2.setType("Border");
        center2.setDailyCapacity(100);
        centerRepository.save(center2);

        ImmigrationOfficer officer1 = new ImmigrationOfficer();
        officer1.setFirstName("Ahmed");
        officer1.setLastName("Al-Rashdi");
        officer1.setBadgeNumber("OFF001");
        officer1.setOfficerRank("Senior");
        officer1.setClearanceLevel(5);
        officer1.setActive(true);
        officer1.setCenter(center1);
        officerRepository.save(officer1);

        ImmigrationOfficer officer2 = new ImmigrationOfficer();
        officer2.setFirstName("Sara");
        officer2.setLastName("Al-Balushi");
        officer2.setBadgeNumber("OFF002");
        officer2.setOfficerRank("Junior");
        officer2.setClearanceLevel(2);
        officer2.setActive(true);
        officer2.setCenter(center1);
        officerRepository.save(officer2);

        BorderControlOfficer borderOfficer = new BorderControlOfficer();
        borderOfficer.setFirstName("Khalid");
        borderOfficer.setLastName("Al-Harthi");
        borderOfficer.setBadgeNumber("BOR001");
        borderOfficer.setOfficerRank("Senior");
        borderOfficer.setClearanceLevel(4);
        borderOfficer.setActive(true);
        borderOfficer.setCenter(center2);
        borderOfficer.setAssignedCheckpoint("Gate A");
        borderOfficer.setK9UnitAssigned(true);
        officerRepository.save(borderOfficer);

        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("John");
        applicant1.setLastName("Smith");
        applicant1.setPassportNumber("PS001");
        applicant1.setNationality("British");
        applicant1.setCriminalRecorde(false);
        applicantRepository.save(applicant1);

        Applicant applicant2 = new Applicant();
        applicant2.setFirstName("Maria");
        applicant2.setLastName("Garcia");
        applicant2.setPassportNumber("PS002");
        applicant2.setNationality("Spanish");
        applicant2.setCriminalRecorde(true);
        applicantRepository.save(applicant2);

        Applicant applicant3 = new Applicant();
        applicant3.setFirstName("Chen");
        applicant3.setLastName("Wei");
        applicant3.setPassportNumber("PS004");
        applicant3.setNationality("Chinese");
        applicant3.setCriminalRecorde(false);
        applicantRepository.save(applicant3);

        AsylumSeeker asylumSeeker = new AsylumSeeker();
        asylumSeeker.setFirstName("Ali");
        asylumSeeker.setLastName("Hassan");
        asylumSeeker.setPassportNumber("PS003");
        asylumSeeker.setNationality("Syrian");
        asylumSeeker.setCriminalRecorde(false);
        asylumSeeker.setCountryOfOrigin("Syria");
        asylumSeeker.setSponsorOrganization("UNHCR");
        applicantRepository.save(asylumSeeker);

        return ResponseEntity.ok("Database seeded successfully!");
    }

}
