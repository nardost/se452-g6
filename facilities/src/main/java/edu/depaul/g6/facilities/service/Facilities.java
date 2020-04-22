package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Facilities {

    private LocationService locationService;

    @Autowired
    public Facilities(LocationService locationService) {
        this.locationService = locationService;
    }

    public List<String> getAllLocations() {
        return locationService.getAllLocations().stream().map(Location::getStreetAddress).collect(Collectors.toList());
    }

    public List<String> getAllLocationsInZipCode(int zipCode) {
        return locationService.getLocationsByZipCode(zipCode).stream().map(Location::getStreetAddress).collect(Collectors.toList());
    }
}
