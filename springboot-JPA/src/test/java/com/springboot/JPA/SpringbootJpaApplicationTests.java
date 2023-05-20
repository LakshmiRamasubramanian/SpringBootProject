package com.springboot.JPA;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.JPA.Entity.StudentEntity;
import com.springboot.JPA.Repository.StudentRepository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaApplicationTests {
	
	@Autowired
    StudentRepository studentRepo;
    
	@Autowired
	
	StudentEntity studentEntity;
	
	@Before
	public  void setStudentEnity() {
		studentEntity.setId(1);
		studentEntity.setName("Lakshmi");
		studentEntity.setTestScore(100);
		
	}
	
	@Test
	public void studentEntityTestByInsert() {
		
		
		studentRepo.save(studentEntity);
		
		StudentEntity studentDetailByID= studentRepo.findById(1).get();
		
		System.out.println(studentDetailByID.getName());
		
		assertNotNull(studentDetailByID);
	}

	@Test
	public void studentEntityTestByUpdate() {
		
		
		studentEntity.setName("Test");
		studentEntity.setTestScore(90);
		studentRepo.save(studentEntity);
		
		StudentEntity studentDetailByID= studentRepo.findById(1).get();
		
		System.out.println(studentDetailByID.getName());
		
		assertEquals("Test",studentDetailByID.getName());
	}
    @Test
	public void studentEntityTestByDelete() {
    	studentRepo.save(studentEntity);
		
		studentRepo.deleteAll();;
		
		List<StudentEntity> studentDetailByID= studentRepo.findAll();
		
		assertEquals(0,studentDetailByID.size());
	}
	@Test
	public void studentEntityTestByRead() {
		
		studentRepo.save(studentEntity);
		
		List<StudentEntity> studentDetailByID= studentRepo.findAll();
		
		System.out.println(studentDetailByID.get(0));
		
		assertNotNull(studentDetailByID);
	}
}
