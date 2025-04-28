package com.example.onlinecoursemanagementsystem.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String fullName;
    private Integer age;
    @Email
    private String email;
    private Long enrollments;

}