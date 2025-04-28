package com.example.onlinecoursemanagementsystem.service;

import com.example.onlinecoursemanagementsystem.dto.CourseDto;
import com.example.onlinecoursemanagementsystem.model.Course;
import com.example.onlinecoursemanagementsystem.model.Instructor;
import com.example.onlinecoursemanagementsystem.model.Result;
import com.example.onlinecoursemanagementsystem.repository.CourseRepo;
import com.example.onlinecoursemanagementsystem.repository.InstructorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepo repo;

    @Autowired
    InstructorRepo instructorRepo;

    public List<Course> findAll() {
        return repo.findAll();
    }

    public Course findById(Long id) {
        return repo.findById(id).get();
    }

    public Result create(CourseDto dto) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setDurationWeeks(dto.getDurationWeeks());

        Optional<Instructor> byId = instructorRepo.findById(dto.getInstructor());
        Instructor instructor = byId.get();
        course.setInstructor(instructor);

        repo.save(course);
        return new Result("Course created successfully", true);
    }

    public Result update(CourseDto dto, Long id) {
        Optional<Course> byId = repo.findById(id);
        if (byId.isPresent()) {
            Course course = byId.get();
            course.setTitle(dto.getTitle());
            course.setDescription(dto.getDescription());
            course.setDurationWeeks(dto.getDurationWeeks());

            Optional<Instructor> byInstructor = instructorRepo.findById(dto.getInstructor());
            Instructor instructor = byInstructor.get();
            course.setInstructor(instructor);

            repo.save(course);
            return new Result("Course updated successfully", true);
        }
        return new Result("Course not found", false);
    }

    public Result delete(Long id) {
        Optional<Course> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Course deleted successfully", true);
        }
        return new Result("Course not found", false);
    }

}