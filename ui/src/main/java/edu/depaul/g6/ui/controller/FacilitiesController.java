package edu.depaul.g6.ui.controller;

import edu.depaul.g6.commons.IdGenerator;
import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.domain.Subscriber;
import edu.depaul.g6.facilities.domain.Subscription;
import edu.depaul.g6.ui.service.FacilitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class FacilitiesController {

    private FacilitiesService facilitiesService;
    private IdGenerator idGenerator;

    @Autowired
    public void setFacilitiesService(FacilitiesService service) {
        this.facilitiesService = service;
    }

    @Autowired
    public void setIdGenerator(IdGenerator generator) {
        this.idGenerator = generator;
    }

    @GetMapping("/locations")
    public String locations(@RequestParam(required = false) Integer zipCode,  Model model) {

        List<Location> locations = facilitiesService.getLocations(zipCode);

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

    /**
     * This should be in AccountsController.
     * I am only writing it because we have to make progress.
     * Change the endpoint to /subscribe
     */
    @GetMapping("/subscribe-fake")
    public String showSubscriptionForm(Model model) {
        Subscriber subscriber = new Subscriber();
        List<String> categories = new ArrayList<>();
        facilitiesService.getAllCategories().forEach(c -> categories.add(c.getCategory()));
        model.addAttribute("categories", categories);
        model.addAttribute("states", facilitiesService.getServiceStates());
        model.addAttribute("subscriber", subscriber);
        return "subscribe";
    }

    @PostMapping("/subscribe-fake")
    public String subscribe(@Valid @ModelAttribute Subscriber subscriber,
                            BindingResult bindingResult) {
        /*
         * (1) Take subscription form input from user.
         * (2) Generate account id.
         * (3) Send activation request to facilities.
         * (4) Confirm to user that activation is pending.
         */
        try {
            facilitiesService.saveSubscriber(subscriber);
        } catch (NoSuchAlgorithmException nsae) {
            /*
             * Redirect to error page 5XX - Internal Server Error
             */
            log.error("NoSuchAlgorithmException thrown. Check the hashing algorithm.");
        }
        log.info(subscriber.getFirstName() + " " + subscriber.getLastName());
        log.info(subscriber.getEmail());
        log.info(subscriber.getStreetAddress() + ", " + subscriber.getCity());
        log.info(subscriber.getMm());
        log.info(subscriber.getServiceType());
        /*
         * This should be the confirmation page.
         */
        return "home";
    }
}
