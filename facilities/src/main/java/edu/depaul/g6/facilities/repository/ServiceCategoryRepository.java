package edu.depaul.g6.facilities.repository;

import edu.depaul.g6.facilities.domain.ServiceCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceCategoryRepository extends MongoRepository<ServiceCategory, String> {
    List<ServiceCategory> findAllByCategoryContaining(String category);
    ServiceCategory findByCategory(String category);
}
