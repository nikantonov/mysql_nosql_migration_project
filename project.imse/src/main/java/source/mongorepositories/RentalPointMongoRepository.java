package source.mongorepositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import source.mongomodels.RentalPoint_mongo;

public interface RentalPointMongoRepository extends MongoRepository<RentalPoint_mongo, String> {
}
