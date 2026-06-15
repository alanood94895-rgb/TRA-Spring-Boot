package com.example.tra.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImmigrationOfficer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String badgeNumber;
    private String officerRank;
    private int clearanceLevel;
    private boolean active;

    @ManyToOne
    private ImmigrationCenter center;

    @OneToMany
    private List<Interview> interviews;
}
