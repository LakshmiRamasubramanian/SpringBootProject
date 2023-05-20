package com.springboot.JPA.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.springboot.JPA.Entity.StudentEntity;

@Service
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

}
