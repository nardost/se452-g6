package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
class LocationService {

    private LocationRepository locationRepository;

    @Autowired
    private LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    List<Location> getAllLocations() {
        return StreamSupport.stream(locationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    /*
     * This is useless since the ID is generated by database and no one knows it.
     */
    Location getLocationById(int id) {
        return locationRepository.findById(id).orElse(null);
    }

    List<Location> getLocationsByZipCode(int zipCode) {
        return locationRepository.findAllByZipCode(zipCode);
    }
}