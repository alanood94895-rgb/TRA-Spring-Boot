package com.example.tra.Services;

import com.example.tra.Entities.ImmigrationCenter;
import com.example.tra.Entities.ImmigrationOfficer;
import com.example.tra.Repositories.CenterRepository;
import com.example.tra.Repositories.OfficerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
    public class OfficerService {

        private final OfficerRepository officerRepository;
        private final CenterRepository centerRepository;

        public ImmigrationOfficer promoteOfficer(Long officerId,
                                                 String newRank,
                                                 int newClearanceLevel) {

            if (newClearanceLevel < 1 || newClearanceLevel > 5) {
                throw new RuntimeException("Clearance level must be between 1 and 5");
            }

            ImmigrationOfficer officer = officerRepository.findById(officerId)
                    .orElseThrow(() -> new RuntimeException("Officer not found"));

            officer.setOfficerRank(newRank);
            officer.setClearanceLevel(newClearanceLevel);

            return officerRepository.save(officer);
        }

        public ImmigrationOfficer transferOfficer(Long officerId,
                                                  Long newCenterId) {

            ImmigrationOfficer officer = officerRepository.findById(officerId)
                    .orElseThrow(() -> new RuntimeException("Officer not found"));

            ImmigrationCenter center = centerRepository.findById(newCenterId)
                    .orElseThrow(() -> new RuntimeException("Center not found"));

            officer.setCenter(center);

            return officerRepository.save(officer);
        }

        public List<ImmigrationOfficer> findOfficersByRank(String rank) {
            return officerRepository.findByOfficerRank(rank);
        }

        public List<ImmigrationOfficer> findOfficersByRank(String rank,
                                                           int minimumClearanceLevel) {

            List<ImmigrationOfficer> officers =
                    officerRepository.findByOfficerRank(rank);

            List<ImmigrationOfficer> result = new ArrayList<>();

            for (ImmigrationOfficer officer : officers) {

                if (officer.getClearanceLevel() >= minimumClearanceLevel) {
                    result.add(officer);
                }
            }

            return result;
        }
    }


