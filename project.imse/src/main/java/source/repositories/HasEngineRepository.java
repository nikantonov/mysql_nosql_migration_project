package source.repositories;

import org.springframework.data.repository.CrudRepository;
import source.models.Car;
import source.models.HasEngine;

public interface HasEngineRepository extends CrudRepository<HasEngine, Long> {
    Iterable<HasEngine> findByCar(Car car);
}
