package com.example.tra;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImmigrationOfficer extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String badgeNumber;
    private String rank;
    private int clearanceLevel;
    private boolean active;

    @ManyToOne
    private ImmigrationCenter center;

    @OneToMany(mappedBy = "officer")
    private List<Interview> interviews;
}
