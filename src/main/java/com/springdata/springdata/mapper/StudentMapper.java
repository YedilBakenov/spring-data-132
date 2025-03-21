package com.springdata.springdata.mapper;


import com.springdata.springdata.dto.StudentDto;
import com.springdata.springdata.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "firstLastName", source = "fullName")
    @Mapping(target = "create", source = "createdAt")
    @Mapping(target = "update", source = "updatedAt")
    StudentDto toDto(Student student);

    @Mapping(target = "fullName", source = "firstLastName")
    @Mapping(target = "createdAt", source = "create")
    @Mapping(target = "updatedAt", source = "update")
    Student toModel(StudentDto studentDto);
    List<StudentDto> toListDto(List<Student> students);
    List<Student> toListModel(List<StudentDto> studentDtoList);
}
