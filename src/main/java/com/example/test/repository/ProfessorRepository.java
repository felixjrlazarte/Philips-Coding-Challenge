package com.example.test.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.test.model.Professor;
import com.example.test.dao.Search;

@Repository
public class ProfessorRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  class ProfessorRowMapper implements RowMapper < Professor > {
    @Override
    public Professor mapRow(ResultSet rs, int rowNum) throws SQLException {
      Professor professor = new Professor();
      professor.setId(rs.getLong("id"));
      professor.setName(rs.getString("name"));
      professor.setDepartmentId(rs.getLong("department_id"));
      return professor;
    }
  }

  class SearchRowMapper implements RowMapper < Search > {
    @Override
    public Search mapRow(ResultSet rs, int rowNum) throws SQLException {
      Search result = new Search();
      result.setName(rs.getString("name"));
      result.setCourses((String[]) rs.getArray("courses").getArray());
      return result;
    }
  }

  public List < Professor > findAll() {
    return jdbcTemplate.query("SELECT * FROM professor", new ProfessorRowMapper());
  }

  public int deleteById(long id) {
    return jdbcTemplate.update("DELETE FROM professor where id=?", new Object[] { id });
  }

  public int insert(Professor professor) {
    return jdbcTemplate.update("INSERT INTO professor (name, department_id) " + "values(?, ?)", new Object[] { professor.getName(), professor.getDepartmentId() });
  }

  public List < Search > findAllProfessor() {
    return jdbcTemplate.query("SELECT t1.id, t1.name, array_agg(t3.name) AS courses FROM professor t1 " +
    "INNER JOIN schedule t2 ON t1.id = t2.professor_id " +
    "INNER JOIN course t3 ON t3.id = t2.course_id " +
    "WHERE t1.id IN (SELECT professor_id FROM schedule) " +
    "GROUP BY t1.id", new SearchRowMapper());
  }
}