package com.springdata.springdata.service;

import com.springdata.springdata.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {


    List<Student> getAllStudents();

    Student addStudent(Student student);

    Student getById(int id);

    Student updateStudent(Student student);

    String deleteStudent(int id);
}
