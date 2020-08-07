package source.repositories;

import org.springframework.data.repository.CrudRepository;
import source.models.Car;
import source.models.CarInformationCard;

public interface CarInformationCardRepository extends CrudRepository<CarInformationCard, Long> {
    CarInformationCard findByCar(Car car);
}
