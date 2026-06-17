package com.example.tra.Controllers;

import com.example.tra.DTOs.CenterDTO;
import com.example.tra.Entities.ImmigrationCenter;
import com.example.tra.Repositories.CenterRepository;
import com.example.tra.Services.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("centers")
public class CenterController {

    @Autowired
    CenterService centerService;


    @PostMapping
    public ResponseEntity<CenterDTO> addCenter(@RequestBody ImmigrationCenter center){
        return ResponseEntity.ok(CenterDTO.convertToDTO(centerService.createCenter(center)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CenterDTO> getCenterById(@PathVariable Long id){
        return ResponseEntity.ok(CenterDTO.convertToDTO(centerService.getCenterByID(id)));
    }
}