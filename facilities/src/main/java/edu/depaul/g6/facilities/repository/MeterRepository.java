package edu.depaul.g6.facilities.repository;

import edu.depaul.g6.facilities.domain.Meter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MeterRepository extends MongoRepository<Meter, String> {
    Meter findFirstByInstalledIsFalse();
}
