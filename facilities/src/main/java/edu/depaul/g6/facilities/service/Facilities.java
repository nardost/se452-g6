package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.Subscription;
import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.domain.ServiceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /**
     * (1) Checks if service is available at address
     *     (do we have a meter installed at that address?)
     * (2) If service is available, create a subscription object and persist it.
     * (3) Send ACTIVATE signal to service delivery and monitoring module.
     * (4) If no meter is installed at address:
     *     a. Schedule an installation job
     *     b. Notify accounts about the scheduled date
     */
    public void activateService(String accountNumber, Location location, String serviceType) {
        subscriptionService.activateService(accountNumber, location, serviceType);
    }

    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    public List<Location> getAllLocationsInZipCode(int zipCode) {
        return locationService.getLocationsByZipCode(zipCode);
    }

    public List<Location> getLocations(Integer zipCode) {
        return (zipCode == null) ? getAllLocations() : getAllLocationsInZipCode(zipCode);
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

    /**
     * This could go into a database. But for the time being,
     * it won't hurt to hard code the list of served states.
     * The list grows as the business grows.
     */
    public List<String> getStatesServed() {
        final String[] STATES_SERVED = new String[] { "IL", "IN", "WI" };
        return Stream.of(STATES_SERVED).collect(Collectors.toList());
    }
}
