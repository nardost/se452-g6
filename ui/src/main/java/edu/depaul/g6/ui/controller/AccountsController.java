package edu.depaul.g6.ui.controller;

import edu.depaul.g6.accounts.repository.AccountRepository;
import edu.depaul.g6.accounts.service.Accounts;
import edu.depaul.g6.accounts.domain.Account;
import edu.depaul.g6.accounts.domain.Subscriber;
import edu.depaul.g6.accounts.service.OutageReportService;
import edu.depaul.g6.accounts.domain.Report;
import edu.depaul.g6.accounts.repository.OutageReportRepository;
import edu.depaul.g6.commons.Utilities;
import edu.depaul.g6.facilities.service.Facilities;
import edu.depaul.g6.ui.config.G6UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private Utilities utilities;

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
        return "index";
    }


    @GetMapping("/logout")
    public String logout(Model model) {
        return "logout";
    }

    /*
    @GetMapping("/outages")
    public String outages() {
        return "outages";
    }
     */

    @GetMapping("/outages")
    public String outageReport(Model model) {
       model.addAttribute("reports", accounts.getAllReports());
        return "outages";
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

            // EXPERIMENTING - CHRISTIAN
            // logs in the user programmatically after registration
            G6UserPrincipal user = new G6UserPrincipal(account, subscriber);
            Authentication auth =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            // EXPERIMENTING

            return "password-reset";
        } catch (NoSuchAlgorithmException nsae) {
            /*
             * Redirect to error page 5XX - Internal Server Error
             */
            log.error("NoSuchAlgorithmException thrown. Check the hashing algorithm.");
            return "index";
        }
    }

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/password-reset")
    public String subscribe(@Valid @ModelAttribute Account account,
                            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("There was an error binding.");
            return "password-reset";
        }

        Account inRepo = accountRepository.findByEmail(account.getEmail());
        inRepo.setPassword(utilities.encodePassword(account.getPassword()));
        accountRepository.save(inRepo);
        return "redirect:/";
    }


    @GetMapping("/account-details")
    public String accountDetails() {
        return "account-details"; // can probably just do everything from Thymeleaf as it can access the principal
    }
}
