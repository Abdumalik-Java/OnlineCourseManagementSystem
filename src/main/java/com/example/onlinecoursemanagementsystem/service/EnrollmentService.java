package com.example.onlinecoursemanagementsystem.service;

import com.example.onlinecoursemanagementsystem.dto.EnrollmentDto;
import com.example.onlinecoursemanagementsystem.model.Course;
import com.example.onlinecoursemanagementsystem.model.Enrollment;
import com.example.onlinecoursemanagementsystem.model.Result;
import com.example.onlinecoursemanagementsystem.model.Student;
import com.example.onlinecoursemanagementsystem.repository.CourseRepo;
import com.example.onlinecoursemanagementsystem.repository.EnrollmentRepo;
import com.example.onlinecoursemanagementsystem.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    @Autowired
    EnrollmentRepo repo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    CourseRepo courseRepo;

    public List<Enrollment> findAll() {
        return repo.findAll();
    }

    public Enrollment findById(Long id) {
        return repo.findById(id).get();
    }

    public Result create(EnrollmentDto dto) {
        Enrollment enrollment = new Enrollment();

        Optional<Student> byId = studentRepo.findById(dto.getStudentId());
        Student student = byId.get();
        enrollment.setStudent(student);

        Optional<Course> byId1 = courseRepo.findById(dto.getCourseId());
        Course course = byId1.get();
        enrollment.setCourse(course);

        repo.save(enrollment);
        return new Result("Enrollment infos created successfully", true);
    }

    public Result update(EnrollmentDto dto, Long id) {
        Optional<Enrollment> byId = repo.findById(id);
        if (byId.isPresent()) {
            Enrollment enrollment = byId.get();

            Optional<Student> byId1 = studentRepo.findById(dto.getStudentId());
            Student student = byId1.get();
            enrollment.setStudent(student);

            Optional<Course> byId2 = courseRepo.findById(dto.getCourseId());
            Course course = byId2.get();
            enrollment.setCourse(course);

            repo.save(enrollment);
            return new Result("Enrollment infos updated successfully", true);
        }
        return new Result("Enrollment not found", false);
    }

    public Result delete(Long id) {
        Optional<Enrollment> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Enrollment deleted successfully", true);
        }
        return new Result("Enrollment not found", false);
    }

}