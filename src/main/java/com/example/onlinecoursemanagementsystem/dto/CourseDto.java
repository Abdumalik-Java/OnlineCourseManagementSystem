package com.example.onlinecoursemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private String title;
    private String description;
    private Integer durationWeeks;
    private Long instructor;

}