package com.springdata.springdata.controller;


import com.springdata.springdata.entity.Student;
import com.springdata.springdata.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;

    @GetMapping()
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping(value = "/add-student")
    public Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping(value = "/details/{id}")
    public Student getStudentById(@PathVariable int id){
        return studentService.getById(id);
    }

    @PutMapping(value = "/update-student")
    public Student updateStudent(@RequestBody Student student){
        return studentService.updateStudent(student);
    }

    @DeleteMapping(value = "/delete-student/{id}")
    public String deleteStudent(@PathVariable int id){
       studentService.deleteStudent(id);
       return "";
    }




}
