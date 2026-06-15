package com.example.tra;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorderControlOfficer extends ImmigrationOfficer {

    private String assignedCheckpoint;
    private boolean k9UnitAssigned;
}
