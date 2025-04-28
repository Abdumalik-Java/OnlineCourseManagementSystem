package com.example.onlinecoursemanagementsystem.repository;

import com.example.onlinecoursemanagementsystem.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
}
