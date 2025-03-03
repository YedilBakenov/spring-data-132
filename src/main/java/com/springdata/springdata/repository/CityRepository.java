package com.springdata.springdata.repository;

import com.springdata.springdata.entity.City;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Integer> {
}
