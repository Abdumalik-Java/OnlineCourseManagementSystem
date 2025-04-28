package com.example.onlinecoursemanagementsystem.repository;

import com.example.onlinecoursemanagementsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {

}
