package edu.depaul.g6.ui.controller;

import edu.depaul.g6.facilities.domain.Subscription;
import edu.depaul.g6.ui.service.FacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FacilitiesController {

    private FacilitiesService facilitiesService;

    @Autowired
    public FacilitiesController(FacilitiesService service) {
        this.facilitiesService = service;
    }

    @GetMapping("/locations")
    public String locations(@RequestParam(required = false) Integer zipCode,  Model model) {

        List<String> locations = facilitiesService.getLocations(zipCode);

        model.addAttribute("locations", locations);
        return "locations";
    }

    @GetMapping("/service-categories")
    public String serviceCategories(Model model) {
        model.addAttribute("categories", facilitiesService.getAllCategories());
        return "service-categories";
    }

    @GetMapping("/subscription")
    public String subscription(@RequestParam String accountNumber, Model model) {
        Subscription subscription = facilitiesService.getSubscription(accountNumber);
        model.addAttribute("subscription", subscription);
        return "subscription";
    }

    @GetMapping("/subscriptions")
    public String subscriptions(Model model) {
        List<Subscription> subscriptions = facilitiesService.getAllSubscriptions();
        model.addAttribute("subscriptions", subscriptions);
        return "subscriptions";
    }
}
