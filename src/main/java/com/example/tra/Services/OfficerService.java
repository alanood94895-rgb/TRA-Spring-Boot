package com.example.tra.Services;

import com.example.tra.Entities.ImmigrationCenter;
import com.example.tra.Entities.ImmigrationOfficer;
import com.example.tra.Repositories.CenterRepository;
import com.example.tra.Repositories.OfficerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfficerService {

    @Autowired
    OfficerRepository officerRepository;

    @Autowired
    CenterRepository centerRepository;

    public ImmigrationOfficer promoteOfficer(Long officerId, String newOfficerRank, int newClearanceLevel1) {
        if (newClearanceLevel1 < 1 || newClearanceLevel1 > 5) { //Access Level of the officer
            throw Exceptions.badRequest("Clearance Level must be between 1 and 5");
        }
        ImmigrationOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> Exceptions.notFound("Officer not found with id: " + officerId));

        officer.setOfficerRank(newOfficerRank);
        officer.setClearanceLevel(newClearanceLevel1);
        return officerRepository.save(officer);
    }

    //Transfer officer to new center
    public ImmigrationOfficer transferOfficer(Long officerId, Long newCenterId) {
        ImmigrationOfficer officer = officerRepository.findById(officerId)
                .orElseThrow(() -> Exceptions.notFound("Officer not found with id: " + officerId));

        ImmigrationCenter center = centerRepository.findById(newCenterId)
                .orElseThrow(() -> Exceptions.notFound("Center not found with id: " + newCenterId));

        officer.setCenter(center);
        return officerRepository.save(officer);
    }

    public List<ImmigrationOfficer> findOfficerByRank(String rank) {
        return officerRepository.findByOfficerRank(rank);
    }

    public List<ImmigrationOfficer> findOfficerByRank(String rank, int minimumClearanceLevel) {
        List<ImmigrationOfficer> officers = officerRepository.findByOfficerRank(rank);
        List<ImmigrationOfficer> result = new ArrayList<>();
        for (ImmigrationOfficer officer : officers) {
            if (officer.getClearanceLevel() >= minimumClearanceLevel) {
                result.add(officer);
            }
        }

        return result;
    }

    public ImmigrationOfficer saveOfficer(ImmigrationOfficer officer){
        if (officer.getFirstName() == null || officer.getFirstName().isEmpty()){
            throw Exceptions.badRequest("First name is required");
        }
        if (officer.getLastName() == null || officer.getLastName().isEmpty()){
            throw Exceptions.badRequest("Last name is required");
        }
        if (officer.getBadgeNumber() == null || officer.getBadgeNumber().isBlank()){
            throw Exceptions.badRequest("Badge number is required");
        }
        return officerRepository.save(officer);
    }

    public ImmigrationOfficer getOfficerById(Long id){
        return officerRepository.findById(id)
                .orElseThrow(() -> Exceptions.notFound("Officer not found with id: " + id));
    }

    public List<ImmigrationOfficer> getAllOfficers(){
        return officerRepository.findAll();
    }
}