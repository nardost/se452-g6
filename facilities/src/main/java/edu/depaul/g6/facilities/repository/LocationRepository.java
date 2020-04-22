package edu.depaul.g6.facilities.repository;

import edu.depaul.g6.facilities.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findAllByZipCode(int zipCode);
}
