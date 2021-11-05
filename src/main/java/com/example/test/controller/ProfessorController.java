package com.example.test.controller;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.test.dao.Search;
import com.example.test.model.Professor;
import com.example.test.repository.ProfessorRepository;

import com.example.test.response.ResponseHandler;

@RestController
@RequestMapping("/api")
public class ProfessorController {
  @Autowired
  private ProfessorRepository professorRepository;

  @GetMapping("/professor")
  public List<Professor> getAllProfessors() {
    return professorRepository.findAll();
  }

  @PostMapping("/professor")
  public ResponseEntity<Object> createProfessor(@Valid @RequestBody Professor professor) {
    try {
      professorRepository.insert(professor);
      return ResponseHandler.generateResponse("Successfully created!", HttpStatus.OK);
    } catch (Exception e) {
        return ResponseHandler.generateResponse("Internal Server Error", HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/professor/{id}")
  public ResponseEntity<Object> deleteProfessor(@PathVariable(value = "id") Long professorId) {
    try {
      int result = professorRepository.deleteById(professorId);

      if (result == 0) {
        return ResponseHandler.generateResponse("Id not found", HttpStatus.BAD_REQUEST);
      }

      return ResponseHandler.generateResponse("Successfully Deleted!", HttpStatus.ACCEPTED);
    } catch (Exception e) {
        return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS);
    }
  }

  @GetMapping("/search")
  public List<Search> search() {
    return professorRepository.findAllProfessor();
  }
}