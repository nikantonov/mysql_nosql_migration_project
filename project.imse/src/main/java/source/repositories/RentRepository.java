package source.repositories;

import org.springframework.data.repository.CrudRepository;
import source.models.Rent;

public interface RentRepository extends CrudRepository<Rent, Long> {
}
