package source.mongorepositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import source.mongomodels.User_mongo;

public interface UserMongoRepository extends MongoRepository<User_mongo, String> {
    User_mongo findById(Long Id);
}
