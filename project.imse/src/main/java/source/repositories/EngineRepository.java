package source.repositories;

import org.springframework.data.repository.CrudRepository;
import source.models.Engine;

public interface EngineRepository extends CrudRepository<Engine, Long> {
    Iterable<Engine> findByType(String type);
}
