package com.springdata.springdata.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseClass{

    private String fullName;

    private double gpa;

    @ManyToMany
    private List<City> cities;
}
