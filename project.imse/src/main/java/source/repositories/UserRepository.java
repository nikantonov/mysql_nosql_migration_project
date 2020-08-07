package source.repositories;

import org.springframework.data.repository.CrudRepository;
import source.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLogin(String login);
}
