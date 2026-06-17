package com.example.tra.Services;

import com.example.tra.Entities.ImmigrationCenter;
import com.example.tra.Repositories.CenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenterService {
    @Autowired
    CenterRepository centerRepository;

    public ImmigrationCenter createCenter(ImmigrationCenter center) {

        if (center.getName() == null || center.getName().isBlank()) {
            throw new RuntimeException("Center name is required");
        }

        if (center.getLocationCountry() == null || center.getLocationCountry().isBlank()) {
            throw new RuntimeException("Location country is required");
        }

        if (center.getType() == null || center.getType().isBlank()) {
            throw new RuntimeException("Center type is required");
        }

        if (center.getDailyCapacity() <= 0) {
            throw new RuntimeException("Daily capacity must be greater than 0");
        }

        return centerRepository.save(center);
    }

    public ImmigrationCenter getCenterByID(Long id) {

        return centerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Center not found with id: " + id));
    }
}