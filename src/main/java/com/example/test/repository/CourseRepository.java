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

import com.example.test.model.Course;

@Repository
public class CourseRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  class CourseRowMapper implements RowMapper < Course > {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
      Course course = new Course();
      course.setId(rs.getLong("id"));
      course.setName(rs.getString("name"));
      course.setDepartmentId(rs.getLong("department_id"));
      course.setCredits(rs.getLong("credits"));
      return course;
    }
  }

  public List < Course > findAll() {
    return jdbcTemplate.query("SELECT * FROM course", new CourseRowMapper());
  }

  public int deleteById(long id) {
    return jdbcTemplate.update("DELETE FROM course where id=?", new Object[] { id });
  }

  public int insert(Course course) {
    return jdbcTemplate.update("INSERT INTO course (name, department_id, credits) " + "values(?, ?, ?)",
    new Object[] { course.getName(), course.getDepartmentId(), course.getCredits() });
  }
}