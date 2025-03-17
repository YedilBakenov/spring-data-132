package com.springdata.springdata.repository;

import com.springdata.springdata.entity.Student;

import java.util.List;

public interface StudentCustomRepository {

    List<Student>getStudentsGpaMore(double gpa);

    List<Student>getStudentByGpaOrCity(Double gpa, String cityName);

    List<Student> getSortedById();
}
