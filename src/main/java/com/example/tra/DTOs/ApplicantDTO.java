package com.example.tra.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicantDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String nationality;
    private String passportNumber;
    private boolean criminalRecorde;

}
