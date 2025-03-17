package com.springdata.springdata.controller;


import com.springdata.springdata.entity.Student;
import com.springdata.springdata.repository.StudentCustomRepository;
import com.springdata.springdata.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;
    private final StudentCustomRepository studentCustomRepository;

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

    @GetMapping(value = "/gpa-more")
    public List<Student> getStudentsByGpaMore(@RequestParam double gpa){
        return studentCustomRepository.getStudentsGpaMore(gpa);
    }

    @GetMapping(value = "/filter")
    public List<Student> getStudentsByGpaAndCity(@RequestParam(required = false) Double gpa,
                                                 @RequestParam(required = false) String cityName){
        return studentCustomRepository.getStudentByGpaOrCity(gpa, cityName);
    }


    @GetMapping(value = "/sorted")
    public List<Student> sorted(){
        return studentCustomRepository.getSortedById();
    }


}
