package com.learningSpring.crudDemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.learningSpring.crudDemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository // support component scanning and translates JDBC exceptions
public class StudentDAOImpl implements StudentDAO {

  // define field for entity manager
  private EntityManager entityManager;

  // inject the entity manager using constructor injection
  @Autowired
  public StudentDAOImpl(EntityManager theEntityManager) {
    this.entityManager = theEntityManager;
  }

  // implement save method
  @Override
  @Transactional // handles transaction management so we don't have to manually start and end
                 // transactions
  public void save(Student theStudent) {
    entityManager.persist(theStudent); // save or update the student to the database
  }

  @Override
  public Student findById(Integer id) {
    return entityManager.find(Student.class, id);
  }

  @Override
  public List<Student> findAll() {
    // create a query
    // using the fields and class name from entity class, not using the table and
    // column name from database
    TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

    // Return query results
    return theQuery.getResultList();
  }

  @Override
  public List<Student> findByLastName(String theLastName) {
    // create a query
    TypedQuery<Student> theQuery = entityManager.createQuery(
        "FROM Student WHERE lastName=:theData",
        Student.class);

    // set query parameters
    theQuery.setParameter("theData", theLastName);

    // return query results
    return theQuery.getResultList();
  }

  @Override
  @Transactional
  public void update(Student theStudent) {
    entityManager.merge(theStudent);
  }

  @Override
  @Transactional
  public void deleteById(Integer id) {
    // retrieve the student
    Student theStudent = entityManager.find(Student.class, id);

    // delete the student
    entityManager.remove(theStudent);
  }

  @Override
  @Transactional
  public int deleteAll() {
    int numRowDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
    return numRowDeleted;
  }

}
