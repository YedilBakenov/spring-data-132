package com.springdata.springdata.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    @PrePersist
    public void preCreate(){
        createdAt = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = LocalDate.now();
    }
}
