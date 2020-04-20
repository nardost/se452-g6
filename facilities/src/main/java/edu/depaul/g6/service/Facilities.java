package edu.depaul.g6.service;

import edu.depaul.g6.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Facilities {

    private LocationService locationService;

    @Autowired
    public Facilities(LocationService locationService) {
        this.locationService = locationService;
    }

    public List<String> getAllServiceLocations() {
        List<Location> locations = locationService.findAllLocations();
        List<String> stringList = new ArrayList<>();
        locations.forEach(location -> {
            stringList.add(location.toString());
        });
        return stringList;
    }
}
