package com.example.onlinecoursemanagementsystem.repository;

import com.example.onlinecoursemanagementsystem.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepo extends JpaRepository<Instructor, Long> {
    boolean existsByEmail(String email);
}
