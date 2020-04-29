package edu.depaul.g6.facilities.repository;

import edu.depaul.g6.facilities.domain.ServiceCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceCategoryRepository extends MongoRepository<ServiceCategory, String> {
}
