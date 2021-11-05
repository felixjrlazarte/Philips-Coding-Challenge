package com.example.test.model;

public class Course {

  private long id;
  private String name;
  private long department_id;
  private long credits;

  public Course() {

  }

  public Course(long id, String name, long department_id, long credits) {
    this.id = id;
    this.name = name;
    this.department_id = department_id;
    this.credits = credits;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getDepartmentId() {
    return department_id;
  }

  public void setDepartmentId(long department_id) {
    this.department_id = department_id;
  }

  public long getCredits() {
    return credits;
  }

  public void setCredits(long credits) {
    this.credits = credits;
  }
}