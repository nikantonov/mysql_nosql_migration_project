package source.mongorepositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import source.mongomodels.Car_mongo;

public interface CarMongoRepository extends MongoRepository<Car_mongo, String> {
    Car_mongo findById(Long Id);
}
