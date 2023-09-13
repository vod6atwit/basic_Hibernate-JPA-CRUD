package com.learningSpring.crudDemo.dao;

import java.util.List;

import com.learningSpring.crudDemo.entity.Student;

public interface StudentDAO {
  // CREATE
  void save(Student theStudent);

  // READ
  Student findById(Integer id);

  List<Student> findAll();

  List<Student> findByLastName(String theLastName);

  // UPDATE
  void update(Student theStudent);

  // DELETE
  void deleteById(Integer id);

  int deleteAll();
}
