package edu.depaul.g6.ui.controller;

import edu.depaul.g6.accounts.service.Accounts;
import edu.depaul.g6.accounts.domain.Account;
import edu.depaul.g6.accounts.domain.Subscriber;
import edu.depaul.g6.facilities.service.Facilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class AccountsController {

    @Value("${spring.application.name}")
    private String applicationName;

    private final Accounts accounts;
    private Facilities facilities;

    @Autowired
    public AccountsController(Accounts accounts){
        this.accounts = accounts;
    }

    @Autowired
    public void setFacilities(Facilities f) {
        this.facilities = f;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("applicationName", applicationName);
        return "home";
    }

    @GetMapping("/subscribe")
    public String showSubscriptionForm(Model model) {
        Subscriber subscriber = new Subscriber();
        List<String> categories = new ArrayList<>();
        facilities.getAllCategories().forEach(c -> categories.add(c.getCategory()));
        model.addAttribute("categories", categories);
        model.addAttribute("states", facilities.getStatesServed());
        model.addAttribute("subscriber", subscriber);
        return "subscribe";
    }

    @PostMapping("/subscribe")
    public String subscribe(@Valid @ModelAttribute Subscriber subscriber,
                            BindingResult bindingResult, Model model) {
        /*
         * (1) Take subscription form input from user.
         * (2) Generate account id.
         * (3) Send activation request to facilities.
         * (4) Confirm to user that activation is pending.
         */
        try {
            final String accountNumber;
            accountNumber = accounts.saveSubscriber(subscriber);
            log.info("The new account number is " + accountNumber);
            log.info(subscriber.getFirstName() + " " + subscriber.getLastName());
            log.info(subscriber.getEmail());
            log.info(subscriber.getStreetAddress() + ", " + subscriber.getCity());
            log.info(subscriber.getMm());
            log.info(subscriber.getServiceType());
            Account account = accounts.getAccount(accountNumber);
            model.addAttribute("account", account);
            return "password-reset";
        } catch (NoSuchAlgorithmException nsae) {
            /*
             * Redirect to error page 5XX - Internal Server Error
             */
            log.error("NoSuchAlgorithmException thrown. Check the hashing algorithm.");
            return "home";
        }
    }
}
