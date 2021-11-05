package com.example.test.model;

public class Schedule {

  private long professor_id;
  private long course_id;
  private long semester;
  private long year;

  public Schedule() {

  }

  public Schedule(long professor_id, long course_id, long semester, long year) {
    this.professor_id = professor_id;
    this.course_id = course_id;
    this.semester = semester;
    this.year = year;
  }

  public long getProfessorId() {
    return professor_id;
  }

  public void setProfessorId(long professor_id) {
    this.professor_id = professor_id;
  }

  public long getCourseId() {
    return course_id;
  }

  public void setCourseId(long course_id) {
    this.course_id = course_id;
  }

  public long getSemester() {
    return semester;
  }

  public void setSemester(long semester) {
    this.semester = semester;
  }

  public long getYear() {
    return year;
  }

  public void setYear(long year) {
    this.year = year;
  }
}