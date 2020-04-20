package edu.depaul.g6.repository;

import edu.depaul.g6.domain.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer> {
}
