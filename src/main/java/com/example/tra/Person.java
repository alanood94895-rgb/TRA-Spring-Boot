package com.example.tra;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class Person {
    @GeneratedValue(strategy = GenerationType.AUTO)
    String firstName;
    String lastName;
    String gender;
    String phoneNumber;
    String email;

}
