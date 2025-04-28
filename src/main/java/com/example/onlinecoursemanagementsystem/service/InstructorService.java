package com.example.onlinecoursemanagementsystem.service;

import com.example.onlinecoursemanagementsystem.dto.InstructorDto;
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
public class InstructorService {

    @Autowired
    InstructorRepo repo;

    @Autowired
    CourseRepo courseRepo;

    public List<Instructor> findAll() {
        return repo.findAll();
    }

    public Instructor findById(Long id) {
        return repo.findById(id).get();
    }

    public Result create(InstructorDto dto) {
        boolean b = repo.existsByEmail(dto.getEmail());
        if (b) {
            return new Result("Email is already exist", false);
        }

        Instructor instructor = new Instructor();
        instructor.setFullName(dto.getFullName());
        instructor.setExperienceYears(dto.getExperienceYears());
        instructor.setEmail(dto.getEmail());

        Optional<Course> byId = courseRepo.findById(dto.getCourses());
        Course course = byId.get();
        instructor.setCourses((List<Course>) course);

        repo.save(instructor);
        return new Result("Successfully created instructor", true);
    }

    public Result update(InstructorDto dto, Long id) {
        Optional<Instructor> byId = repo.findById(id);
        if (byId.isPresent()) {
            Instructor instructor = byId.get();
            instructor.setFullName(dto.getFullName());
            instructor.setExperienceYears(dto.getExperienceYears());
            instructor.setEmail(dto.getEmail());

            Optional<Course> byCourse = courseRepo.findById(dto.getCourses());
            Course course = byCourse.get();
            instructor.setCourses((List<Course>) course);

            repo.save(instructor);
            return new Result("Successfully updated instructor", true);
        }
        return new Result("Not found", false);
    }

    public Result delete(Long id) {
        Optional<Instructor> byId = repo.findById(id);
        if (byId.isPresent()) {
            repo.delete(byId.get());
            return new Result("Successfully deleted instructor", true);
        }
        return new Result("Not found", false);
    }

}