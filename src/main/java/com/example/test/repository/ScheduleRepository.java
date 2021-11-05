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

import com.example.test.model.Schedule;

@Repository
public class ScheduleRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  class ScheduleRowMapper implements RowMapper < Schedule > {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
      Schedule schedule = new Schedule();
      schedule.setProfessorId(rs.getLong("professor_id"));
      schedule.setCourseId(rs.getLong("course_id"));
      schedule.setSemester(rs.getLong("semester"));
      schedule.setYear(rs.getLong("year"));
      return schedule;
    }
  }

  public List < Schedule > findAll() {
    return jdbcTemplate.query("SELECT * FROM schedule", new ScheduleRowMapper());
  }

  public int deleteById(long professor_id, long course_id) {
    return jdbcTemplate.update("DELETE FROM schedule where professor_id=? AND course_id=?", new Object[] { professor_id, course_id });
  }

  public int insert(Schedule schedule) {
    return jdbcTemplate.update("INSERT INTO schedule (professor_id, course_id, semester, year) " + "values(?, ?, ?, ?)",
    new Object[] { schedule.getProfessorId(), schedule.getCourseId(), schedule.getSemester(), schedule.getYear() });
  }
}