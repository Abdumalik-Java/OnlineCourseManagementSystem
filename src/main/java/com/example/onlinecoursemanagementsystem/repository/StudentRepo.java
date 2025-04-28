package com.example.onlinecoursemanagementsystem.repository;

import com.example.onlinecoursemanagementsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
    boolean existsByEmail(String email);
}