package com.springdata.springdata.controller;


import com.springdata.springdata.dto.StudentDto;
import com.springdata.springdata.entity.Student;
import com.springdata.springdata.mapper.StudentMapper;
import com.springdata.springdata.repository.StudentCustomRepository;
import com.springdata.springdata.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;
    private final StudentCustomRepository studentCustomRepository;
    private final StudentMapper studentMapper;

    @GetMapping()
    public List<StudentDto> getAllStudents(){
        return studentMapper.toListDto(studentService.getAllStudents());
    }

    @PostMapping(value = "/add-student")
    public StudentDto addStudent(@RequestBody StudentDto student){
        return studentMapper.toDto(studentService.addStudent(studentMapper.toModel(student)));
    }

    @GetMapping(value = "/details/{id}")
    public StudentDto getStudentById(@PathVariable int id){
        return studentMapper.toDto(studentService.getById(id));
    }

    @PutMapping(value = "/update-student")
    public StudentDto updateStudent(@RequestBody Student student){
        return studentMapper.toDto(studentService.updateStudent(student));
    }

    @DeleteMapping(value = "/delete-student/{id}")
    public String deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
       return "";
    }

    @GetMapping(value = "/gpa-more")
    public List<StudentDto> getStudentsByGpaMore(@RequestParam double gpa){
        return studentMapper.toListDto(studentCustomRepository.getStudentsGpaMore(gpa));
    }

    @GetMapping(value = "/filter")
    public List<StudentDto> getStudentsByGpaAndCity(@RequestParam(required = false) Double gpa,
                                                 @RequestParam(required = false) String cityName){
        return studentMapper.toListDto(studentCustomRepository.getStudentByGpaOrCity(gpa, cityName));
    }


    @GetMapping(value = "/sorted")
    public List<StudentDto> sorted(){
        return studentMapper.toListDto(studentCustomRepository.getSortedById());
    }

    @GetMapping(value = "/page")
    public Page<Student> pagination(@RequestParam(defaultValue ="0") int page,
                                    @RequestParam(defaultValue = "5") int size,
                                    @RequestParam(defaultValue = "id") String sortBy,
                                    @RequestParam(defaultValue = "asc") String direction){
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return studentService.getAllStudentsPagination(pageable);
    }

    @GetMapping(value = "/gpa-pagination")
    public Page<Student> paginationGpa(@RequestParam double gpa,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestParam(defaultValue = "desc") String direction){
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by("gpa").ascending(): Sort.by("gpa").descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return studentService.getGpaMoreThanParameter(gpa, pageable);
    }


}
