package com.example.test.controller;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.test.model.Department;
import com.example.test.repository.DepartmentRepository;

import com.example.test.response.ResponseHandler;

@RestController
@RequestMapping("/api")
public class DepartmentController {
  @Autowired
  private DepartmentRepository departmentRepository;

  @GetMapping("/department")
  public List<Department> getAllDepartments() {
    return departmentRepository.findAll();
  }

  @PostMapping("/department")
  public ResponseEntity<Object> createDepartment(@Valid @RequestBody Department department) {
    try {
      departmentRepository.insert(department);
      return ResponseHandler.generateResponse("Successfully created!", HttpStatus.OK);
    } catch (Exception e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
    }
  }

  @DeleteMapping("/department/{id}")
  public ResponseEntity<Object> deleteDepartment(@PathVariable(value = "id") Long departmentId) {
    try {
      int result = departmentRepository.deleteById(departmentId);

      if (result == 0) {
        return ResponseHandler.generateResponse("Id not found", HttpStatus.BAD_REQUEST);
      }

      return ResponseHandler.generateResponse("Successfully Deleted!", HttpStatus.ACCEPTED);
    } catch (Exception e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
    }
  }
}