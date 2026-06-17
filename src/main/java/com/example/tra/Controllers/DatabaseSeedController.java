package com.example.tra.Controllers;

import com.example.tra.Entities.*;
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
        center1.setName("Muscat Immigration Center");
        center1.setLocationCountry("Oman");
        center1.setType("Processing");
        center1.setDailyCapacity(120);
        centerRepository.save(center1);

        ImmigrationCenter center2 = new ImmigrationCenter();
        center2.setName("Salalah Border Office");
        center2.setLocationCountry("Oman");
        center2.setType("Border Control");
        center2.setDailyCapacity(80);
        centerRepository.save(center2);

        ImmigrationOfficer officer1 = new ImmigrationOfficer();
        officer1.setFirstName("Bader");
        officer1.setLastName("Al-Harthi");
        officer1.setBadgeNumber("OFF101");
        officer1.setOfficerRank("Senior Officer");
        officer1.setClearanceLevel(5);
        officer1.setActive(true);
        officer1.setCenter(center1);
        officerRepository.save(officer1);

        ImmigrationOfficer officer2 = new ImmigrationOfficer();
        officer2.setFirstName("Abdullah");
        officer2.setLastName("Al-Riyami");
        officer2.setBadgeNumber("OFF102");
        officer2.setOfficerRank("Junior Officer");
        officer2.setClearanceLevel(3);
        officer2.setActive(true);
        officer2.setCenter(center1);
        officerRepository.save(officer2);

        BorderControlOfficer officer3 = new BorderControlOfficer();
        officer3.setFirstName("Khalid");
        officer3.setLastName("Al-Balushi");
        officer3.setBadgeNumber("BOR201");
        officer3.setOfficerRank("Border Supervisor");
        officer3.setClearanceLevel(4);
        officer3.setActive(true);
        officer3.setCenter(center2);
        officer3.setAssignedCheckpoint("Salalah Airport");
        officer3.setK9UnitAssigned(true);
        officerRepository.save(officer3);

        Applicant applicant1 = new Applicant();
        applicant1.setFirstName("Mohammed");
        applicant1.setLastName("Al-Lawati");
        applicant1.setPassportNumber("PA1001");
        applicant1.setNationality("Omani");
        applicant1.setCriminalRecord(false);
        applicantRepository.save(applicant1);

        Applicant applicant2 = new Applicant();
        applicant2.setFirstName("Fatma");
        applicant2.setLastName("Al-Maamari");
        applicant2.setPassportNumber("PA1002");
        applicant2.setNationality("Emirati");
        applicant2.setCriminalRecord(true);
        applicantRepository.save(applicant2);

        Applicant applicant3 = new Applicant();
        applicant3.setFirstName("Ali");
        applicant3.setLastName("Al-Hashmi");
        applicant3.setPassportNumber("PA1003");
        applicant3.setNationality("Egyptian");
        applicant3.setCriminalRecord(false);
        applicantRepository.save(applicant3);

        AsylumSeeker asylumSeeker = new AsylumSeeker();
        asylumSeeker.setFirstName("Omar");
        asylumSeeker.setLastName("Al-Siyabi");
        asylumSeeker.setPassportNumber("PA1004");
        asylumSeeker.setNationality("Sudanese");
        asylumSeeker.setCriminalRecord(false);
        asylumSeeker.setCountryOfOrigin("Sudan");
        asylumSeeker.setSponsorOrganization("UNHCR");
        applicantRepository.save(asylumSeeker);

        return ResponseEntity.ok("Database seeded successfully!");
    }

}
