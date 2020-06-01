package edu.depaul.g6.facilities.service;

import edu.depaul.g6.delivery.service.ActivationNotifier;
import edu.depaul.g6.facilities.ServiceStatus;
import edu.depaul.g6.facilities.domain.Subscription;
import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private LocationService locationService;
    private MeterService meterService;
    private ActivationNotifier activationNotifier;

    @Autowired
    SubscriptionService(SubscriptionRepository repository) {
        this.subscriptionRepository = repository;
    }

    @Autowired
    void setLocationService(LocationService service) {
        this.locationService = service;
    }

    @Autowired
    void setMeterService(MeterService service) {
        this.meterService = service;
    }

    @Autowired
    void setActivationNotifier(ActivationNotifier notifier) {
        this.activationNotifier = notifier;
    }

    Subscription getSubscription(String accountNumber) {
        return subscriptionRepository.findById(accountNumber).orElse(null);
    }

    List<Subscription> getAllSubscriptions() {
        return StreamSupport.stream(subscriptionRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    void saveSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    /**
     *
     */
    void activateService(String accountNumber, Location location, String serviceType) {
        /*
         * Create a Location object and set its fields.
         */
        final String streetAddress = location.getStreetAddress();
        final String unit = location.getUnit();
        final String city = location.getCity();
        final String state = location.getState();
        final int zipCode = location.getZipCode();
        final String locationString = streetAddress + unit + city + state + zipCode;
        /*
         * hashCode is supposed to be unique.
         */
        final int id = locationString.hashCode();
        location.setId(id);
        /*
         * What is the MAC address of the smart meter
         * installed at the location? Get is from the
         * MeterService.
         */
        final String MAC_ADDRESS = meterService.getMeter().getMac();
        location.setMeterMacAddress(MAC_ADDRESS);

        /*
         * If the location is already in our database, what should we do?
         * (1) Check if there is active subscription at location
         *     (1.1) Proceed normally if there is no active subscription.
         *     (1.2) If there is active subscription, mark the subscription
         *           as invalid.
         */
        ServiceStatus status;
        if(locationService.getLocationById(location.getId()) == null) {
            /*
             * Save location - There is a reference to it
             * in Subscription, so the location must exist
             * before the subscription can be persisted.
             */
            locationService.saveLocation(location);
            status = ServiceStatus.ACTIVATED;
        } else {
            status = ServiceStatus.INVALID;
            log.info("Location exists: " + location.getStreetAddress());
        }

        /*
         * Construct a Subscription object
         * and hand it over to SubscriptionService.
         */
        Subscription subscription = new Subscription();
        subscription.setId(accountNumber);
        subscription.setLocation(location);
        subscription.setServiceCategory(serviceType);
        subscription.setActivationTimeStamp(Timestamp.from(Instant.now()));
        subscription.setServiceStatus(status);
        saveSubscription(subscription);
        /*
         * What is the activation message and when should it be applied?
         * ACTIVATE:ON_TIMESTAMP
         */
        final String ACTIVATION_MESSAGE = "activate:" + subscription.getActivationTimeStamp().toString();
        activationNotifier.sendSignal(MAC_ADDRESS, ACTIVATION_MESSAGE);
    }

    Subscription getByLocation(Location l) { return subscriptionRepository.findByLocation(l); }
}
