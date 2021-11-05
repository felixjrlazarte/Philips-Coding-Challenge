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

import com.example.test.model.Department;

@Repository
public class DepartmentRepository {
  @Autowired
  JdbcTemplate jdbcTemplate;

  class DepartmentRowMapper implements RowMapper < Department > {
    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
      Department department = new Department();
      department.setId(rs.getLong("id"));
      department.setName(rs.getString("name"));
      return department;
    }
  }

  public List < Department > findAll() {
    return jdbcTemplate.query("SELECT * FROM department", new DepartmentRowMapper());
  }

  public int deleteById(long id) {
    return jdbcTemplate.update("DELETE FROM department where id=?", new Object[] { id });
  }

  public int insert(Department department) {
    return jdbcTemplate.update("INSERT INTO department (name) " + "values(?)", new Object[] { department.getName() });
  }
}