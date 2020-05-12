package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.domain.ServiceCategory;
import edu.depaul.g6.facilities.domain.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nardos
 *
 * This class is is the Façade into the
 * facilities module. It should be the only
 * way other modules access facilities functionalities.
 */
@Service
public class Facilities {

    private LocationService locationService;
    private ServiceCategoryService serviceCategoryService;
    private SubscriptionService subscriptionService;

    @Autowired
    public void setLocationService(LocationService service) {
        this.locationService = service;
    }

    @Autowired
    public void setServiceCategoryService(ServiceCategoryService service) {
        this.serviceCategoryService = service;
    }

    @Autowired
    public void setSubscriptionService(SubscriptionService service) {
        this.subscriptionService = service;
    }

    public List<Location> getAllLocations() {
        return locationService.getAllLocations();//.stream();.map(Location::getStreetAddress).collect(Collectors.toList());
    }

    public List<Location> getAllLocationsInZipCode(int zipCode) {
        return locationService.getLocationsByZipCode(zipCode);//.stream().map(Location::getStreetAddress).collect(Collectors.toList());
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
