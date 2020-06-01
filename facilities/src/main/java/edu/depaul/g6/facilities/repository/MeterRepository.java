package edu.depaul.g6.facilities.repository;

import edu.depaul.g6.facilities.domain.Meter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeterRepository extends MongoRepository<Meter, String> {
    Meter findFirstByInstalledIsFalse();
}
