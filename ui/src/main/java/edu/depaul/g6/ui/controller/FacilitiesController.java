package edu.depaul.g6.ui.controller;

import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.domain.Subscription;
import edu.depaul.g6.facilities.service.Facilities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class FacilitiesController {

    private Facilities facilities;

    @Autowired
    public void setFacilities(Facilities f) {
        this.facilities = f;
    }

    @GetMapping("/locations")
    public String locations(@RequestParam(required = false) Integer zipCode,  Model model) {

        List<Location> locations = facilities.getLocations(zipCode);

        model.addAttribute("locations", locations);
        return "locations";
    }

    @GetMapping("/service-categories")
    public String serviceCategories(Model model) {
        model.addAttribute("categories", facilities.getAllCategories());
        return "service-categories";
    }

    @GetMapping("/subscription")
    public String subscription(@RequestParam String accountNumber, Model model) {
        Subscription subscription = facilities.getSubscription(accountNumber);
        model.addAttribute("subscription", subscription);
        return "subscription";
    }

    @GetMapping("/subscriptions")
    public String subscriptions(Model model) {
        List<Subscription> subscriptions = facilities.getAllSubscriptions();
        model.addAttribute("subscriptions", subscriptions);
        return "subscriptions";
    }
}
