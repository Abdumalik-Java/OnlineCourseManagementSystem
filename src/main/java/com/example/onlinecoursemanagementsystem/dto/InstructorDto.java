package com.example.onlinecoursemanagementsystem.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDto {

    private String fullName;
    private Integer experienceYears;
    @Email
    private String email;
    private Long courses;

}