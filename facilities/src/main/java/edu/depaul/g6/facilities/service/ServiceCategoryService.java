package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.ServiceCategory;
import edu.depaul.g6.facilities.repository.ServiceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCategoryService {

    private final ServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    ServiceCategoryService(ServiceCategoryRepository repository) {
        this.serviceCategoryRepository = repository;
    }

    ServiceCategory getCategory(String id) {
        return serviceCategoryRepository.findById(id).get();
    }

    List<ServiceCategory> getAllCategories() {
        return serviceCategoryRepository.findAll();
    }
}
