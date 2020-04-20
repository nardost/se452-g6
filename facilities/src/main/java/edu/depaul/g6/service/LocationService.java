package edu.depaul.g6.service;

import edu.depaul.g6.domain.Location;
import edu.depaul.g6.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
class LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAllLocations() {
        return StreamSupport.stream(locationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

}
