package com.example.test.controller;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.test.model.Schedule;
import com.example.test.repository.ScheduleRepository;

import com.example.test.response.ResponseHandler;

@RestController
@RequestMapping("/api")
public class ScheduleController {
  @Autowired
  private ScheduleRepository scheduleRepository;

  @GetMapping("/schedule")
  public List<Schedule> getAllSchedule() {
    return scheduleRepository.findAll();
  }

  @PostMapping("/schedule")
  public ResponseEntity<Object> createSchedule(@Valid @RequestBody Schedule schedule) {
    try {
      scheduleRepository.insert(schedule);
      return ResponseHandler.generateResponse("Successfully created!", HttpStatus.OK);
    } catch (Exception e) {
        return ResponseHandler.generateResponse("Internal Server Error", HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/schedule")
  public ResponseEntity<Object> deleteSchedule(
    @RequestParam(value = "professor_id") Long profId,
    @RequestParam(value = "course_id") Long courseId) {
    try {
      int result = scheduleRepository.deleteById(profId, courseId);

      if (result == 0) {
        return ResponseHandler.generateResponse("Id not found", HttpStatus.BAD_REQUEST);
      }

      return ResponseHandler.generateResponse("Successfully Deleted!", HttpStatus.ACCEPTED);
    } catch (Exception e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
    }
  }
}