package edu.depaul.g6.facilities.repository;

import edu.depaul.g6.facilities.domain.ServiceCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ServiceCategoryRepository extends MongoRepository<ServiceCategory, String> {
    List<ServiceCategory> findAllByCategoryContaining(String category);
}
