package source.repositories;

import org.springframework.data.repository.CrudRepository;
import source.models.RentalPoint;


public interface RentalPointRepository extends CrudRepository<RentalPoint, Long> {
}
