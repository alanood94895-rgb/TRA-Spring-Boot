package com.example.tra.Controllers;

import com.example.tra.DTOs.OfficerDTO;
import com.example.tra.Entities.BorderControlOfficer;
import com.example.tra.Entities.ImmigrationOfficer;
import com.example.tra.Repositories.OfficerRepository;
import com.example.tra.Services.OfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("officers")
public class OfficerController {
    @Autowired
    OfficerService officerService;

    @PostMapping
    public ResponseEntity<OfficerDTO> hireOfficer(@RequestBody ImmigrationOfficer officer){
        return ResponseEntity.ok(OfficerDTO.convertToDTO(officerService.saveOfficer(officer)));
    }

    @PostMapping("/border")
    public ResponseEntity<OfficerDTO> hireBorderOfficer(@RequestBody BorderControlOfficer officer) {
        return ResponseEntity.ok(OfficerDTO.convertToDTO(officerService.saveOfficer(officer)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfficerDTO> getOfficerById(@PathVariable Long id){
        return ResponseEntity.ok(OfficerDTO.convertToDTO(officerService.getOfficerById(id)));
    }

    @GetMapping
    public ResponseEntity<List<OfficerDTO>> getAllOfficers(){
        return ResponseEntity.ok(OfficerDTO.convertToDTO(officerService.getAllOfficers()));
    }

    @PutMapping("/{id}/promote")
    public ResponseEntity<OfficerDTO> promoteOfficer(@PathVariable Long id, @RequestParam String rank, @RequestParam int clearance){
        return ResponseEntity.ok(OfficerDTO.convertToDTO(officerService.promoteOfficer(id,rank,clearance)));
    }

    @PutMapping("/{id}/transfer/{centerId}")
    public ResponseEntity<OfficerDTO> transferOfficer(@PathVariable Long id, @PathVariable Long centerId){
        return ResponseEntity.ok(OfficerDTO.convertToDTO(officerService.transferOfficer(id, centerId)));
    }
}