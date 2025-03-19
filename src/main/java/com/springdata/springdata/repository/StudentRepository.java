package com.springdata.springdata.repository;

import com.springdata.springdata.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findAll(Pageable pageable);

    Page<Student> findByGpaGreaterThan(double gpa, Pageable pageable);

    Page<Student> findByIdGreaterThanEqual(int id, Pageable pageable);

}
