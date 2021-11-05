package com.example.test.dao;

public class Search {

  private String name;
  private String[] courses;

  public Search() {
    
  }

  public Search(String name, String[] courses) {
    this.name = name;
    this.courses = courses;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String[] getCourses() {
    return courses;
  }

  public void setCourses(String[] courses) {
    this.courses = courses;
  }
}