package edu.depaul.g6.ui;

import edu.depaul.g6.service.Facilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FacilitiesController {

    private Facilities facilities;

    @Autowired
    public FacilitiesController(Facilities facilities) {
        this.facilities = facilities;
    }

    @GetMapping("/locations")
    public String locations(Model model) {
        model.addAttribute("locations", facilities.getAllLocationsInZipCode(60601));
        return "locations";
    }
}
