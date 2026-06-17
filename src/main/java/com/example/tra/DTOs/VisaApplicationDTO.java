package com.example.tra.DTOs;

import com.example.tra.Entities.VisaApplication;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class VisaApplicationDTO {
    private Long id;
    private String visaType;
    private String status;
    private String officerNotes;
    private Long applicantId;
    private Long officerId;


    public static VisaApplicationDTO convertToDTO(VisaApplication visa) {
        VisaApplicationDTO dto = new VisaApplicationDTO();
        dto.setId(visa.getId());
        dto.setVisaType(visa.getVisaType());
        dto.setStatus(visa.getStatus());
        dto.setOfficerNotes(visa.getOfficerNotes());
        dto.setApplicantId(visa.getApplicant().getId());
        dto.setOfficerId(visa.getHandlingOfficer().getId());
        return dto;
    }
    public static List<VisaApplicationDTO> convertToDTO(List<VisaApplication> visas) {
        List<VisaApplicationDTO> dtos = new ArrayList<>();
        for (VisaApplication visa : visas) {
            dtos.add(convertToDTO(visa));
        }
        return dtos;
    }
}
