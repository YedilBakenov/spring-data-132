package com.springdata.springdata.repository3;

import com.springdata.springdata.entity3.Car;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, ObjectId> {
}
