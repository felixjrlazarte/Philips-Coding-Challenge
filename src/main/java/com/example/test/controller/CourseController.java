package com.example.test.controller;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.test.model.Course;
import com.example.test.repository.CourseRepository;

import com.example.test.response.ResponseHandler;

@RestController
@RequestMapping("/api")
public class CourseController {
  @Autowired
  private CourseRepository courseRepository;

  @GetMapping("/course")
  public List<Course> getAllCourse() {
    return courseRepository.findAll();
  }

  @PostMapping("/course")
  public ResponseEntity<Object> createCourse(@Valid @RequestBody Course course) {
    try {
      courseRepository.insert(course);
      return ResponseHandler.generateResponse("Successfully created!", HttpStatus.OK);
    } catch (Exception e) {
        return ResponseHandler.generateResponse("Internal Server Error", HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/course/{id}")
  public ResponseEntity<Object> deleteCourse(@PathVariable(value = "id") Long courseId) {
    try {
      int result = courseRepository.deleteById(courseId);

      if (result == 0) {
        return ResponseHandler.generateResponse("Id not found", HttpStatus.BAD_REQUEST);
      }

      return ResponseHandler.generateResponse("Successfully Deleted!", HttpStatus.ACCEPTED);
    } catch (Exception e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
    }
  }
}