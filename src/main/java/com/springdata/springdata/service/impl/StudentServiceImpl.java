package com.springdata.springdata.service.impl;

import com.springdata.springdata.dto.StudentDto;
import com.springdata.springdata.entity.Student;
import com.springdata.springdata.repository.StudentRepository;
import com.springdata.springdata.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentsRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        return studentsRepository.save(student);
    }

    @Override
    public Student getById(int id) {
        return studentsRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Student student) {
        return studentsRepository.save(student);
    }

    @Override
    public String deleteStudent(int id) {
        Student student = studentsRepository.findById(id).get();
        studentsRepository.deleteById(id);
        return student.getFullName();
    }

    @Override
    public Page<Student> getAllStudentsPagination(Pageable pageable) {
        return studentsRepository.findAll(pageable);
    }

    @Override
    public Page<Student> getGpaMoreThanParameter(double gpa, Pageable pageable) {
        return studentsRepository.findByGpaGreaterThan(gpa, pageable);
    }

    @Override
    public StudentDto convertToDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setGpa(student.getGpa());
        studentDto.setCreate(student.getCreatedAt());
        studentDto.setUpdate(student.getUpdatedAt());
        studentDto.setFirstLastName(student.getFullName());
        studentDto.setCities(student.getCities());
        return studentDto;

    }

    @Override
    public List<StudentDto> getStudentsDto(List<Student> students) {

        List<StudentDto> studentDtoList = new ArrayList<>();

        for(Student st: students){
            studentDtoList.add(convertToDto(st));
        }

        return studentDtoList;
    }
}
