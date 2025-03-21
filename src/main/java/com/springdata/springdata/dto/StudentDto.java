package com.springdata.springdata.dto;


import com.springdata.springdata.entity.City;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private int id;
    private LocalDate create;
    private LocalDate update;
    private String firstLastName;
    private double gpa;
    private List<City> cities;

}
