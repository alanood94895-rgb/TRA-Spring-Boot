package com.example.tra.DTOs;

import com.example.tra.Entities.ImmigrationCenter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CenterDTO {
    private Long id;
    private String name;
    private String locationCountry;
    private String type;
    private int dailyCapacity;

    public static CenterDTO convertToDTO(ImmigrationCenter center) {
        CenterDTO dto = new CenterDTO();
        dto.setId(center.getId());
        dto.setName(center.getName());
        dto.setLocationCountry(center.getLocationCountry());
        dto.setType(center.getType());
        dto.setDailyCapacity(center.getDailyCapacity());
        return dto;
}
