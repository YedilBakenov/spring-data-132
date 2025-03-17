package com.springdata.springdata.repository;

import com.springdata.springdata.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentCustomRepositoryImpl implements StudentCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getStudentsGpaMore(double gpa) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        Predicate gpaPredicate = cb.greaterThan(root.get("gpa"), gpa);
        query.select(root).where(gpaPredicate);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Student> getStudentByGpaOrCity(Double gpa, String cityName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);
        List<Predicate> predicates = new ArrayList<>();

        if (gpa != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("gpa"), gpa));
        }
        if (cityName != null) {
            predicates.add(cb.like(root.join("cities", JoinType.LEFT).get("name"), "%" + cityName + "%"));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Student> getSortedById() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        query.select(root).orderBy(cb.desc(root.get("id")));

        return  entityManager.createQuery(query).getResultList();
    }
}
