package com.springdata.springdata.service;

import com.springdata.springdata.dto.StudentDto;
import com.springdata.springdata.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    List<Student> getAllStudents();

    Student addStudent(Student student);

    Student getById(int id);

    Student updateStudent(Student student);

    String deleteStudent(int id);

    Page<Student> getAllStudentsPagination(Pageable pageable);

    Page<Student> getGpaMoreThanParameter(double gpa, Pageable pageable);

    StudentDto convertToDto(Student student);

    List<StudentDto> getStudentsDto(List<Student> students);
}
