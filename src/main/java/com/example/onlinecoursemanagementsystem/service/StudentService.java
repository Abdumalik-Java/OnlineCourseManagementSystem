package com.example.onlinecoursemanagementsystem.service;

import com.example.onlinecoursemanagementsystem.dto.StudentDto;
import com.example.onlinecoursemanagementsystem.model.Enrollment;
import com.example.onlinecoursemanagementsystem.model.Result;
import com.example.onlinecoursemanagementsystem.model.Student;
import com.example.onlinecoursemanagementsystem.repository.EnrollmentRepo;
import com.example.onlinecoursemanagementsystem.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo repo;

    @Autowired
    EnrollmentRepo enrollmentRepo;

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Student getStudentById(Long id) {
        return repo.findById(id).get();
    }

    public Optional<Student> getStudentByEmail(String email) {
        return repo.findByEmail(email);
    }

    public Result create(StudentDto dto) {
        boolean b = repo.existsByEmail(dto.getEmail());
        if (b) {
            return new Result("Email is already exist", false);
        }

        Student student = new Student();
        student.setFullName(dto.getFullName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        Optional<Enrollment> byId = enrollmentRepo.findById(dto.getEnrollments());
        Enrollment enrollment = byId.get();
        student.setEnrollments((List<Enrollment>) enrollment);

        repo.save(student);
        return new Result("Enrollment created successfully", true);
    }

    public Result update(StudentDto dto, Long id) {
        Optional<Student> byId = repo.findById(id);
        if (byId.isPresent()) {
            Student student = byId.get();
            student.setFullName(dto.getFullName());
            student.setAge(dto.getAge());
            student.setEmail(dto.getEmail());

            Optional<Enrollment> byEnrollment = enrollmentRepo.findById(id);
            Enrollment enrollment = byEnrollment.get();
            student.setEnrollments((List<Enrollment>) enrollment);

            repo.save(student);
            return new Result("Enrollment updated successfully", true);
        }
        return new Result("Enrollment not found", false);
    }

    public Result delete(Long id) {
        Optional<Student> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Enrollment deleted successfully", true);
        }
        return new Result("Enrollment not found", false);
    }

}