package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.domain.ServiceCategory;
import edu.depaul.g6.facilities.domain.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Facilities {

    private LocationService locationService;
    private ServiceCategoryService serviceCategoryService;
    private SubscriptionService subscriptionService;

    @Autowired
    public Facilities(LocationService locationService,
                      ServiceCategoryService serviceCategoryService,
                      SubscriptionService subscriptionService) {
        this.locationService = locationService;
        this.serviceCategoryService = serviceCategoryService;
        this.subscriptionService = subscriptionService;
    }

    public List<String> getAllLocations() {
        return locationService.getAllLocations().stream().map(Location::getStreetAddress).collect(Collectors.toList());
    }

    public List<String> getAllLocationsInZipCode(int zipCode) {
        return locationService.getLocationsByZipCode(zipCode).stream().map(Location::getStreetAddress).collect(Collectors.toList());
    }

    public ServiceCategory getCategory(String id) {
        return serviceCategoryService.getCategory(id);
    }

    public List<ServiceCategory> getAllCategories() {
        return serviceCategoryService.getAllCategories();
    }

    public Subscription getSubscription(String accountNumber) {
        return subscriptionService.getSubscription(accountNumber);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }
}
