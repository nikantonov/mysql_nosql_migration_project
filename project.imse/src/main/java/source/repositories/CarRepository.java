package source.repositories;

import org.springframework.data.repository.CrudRepository;
import source.models.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
    Iterable<Car> findByMaker(String maker);
}
