package edu.depaul.g6.facilities.repository;

import edu.depaul.g6.facilities.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    List<Location> findByZipCode(int zipCode);
}
