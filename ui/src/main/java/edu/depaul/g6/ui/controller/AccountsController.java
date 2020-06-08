package edu.depaul.g6.ui.controller;

import edu.depaul.g6.accounts.domain.Report;
import edu.depaul.g6.accounts.repository.AccountRepository;
import edu.depaul.g6.accounts.repository.OutageReportRepository;
import edu.depaul.g6.accounts.service.Accounts;
import edu.depaul.g6.accounts.domain.Account;
import edu.depaul.g6.accounts.domain.Subscriber;
import edu.depaul.g6.commons.Utilities;
import edu.depaul.g6.facilities.service.Facilities;
import edu.depaul.g6.ui.config.G6UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    private final Facilities facilities;
    private final Utilities utilities;
    private final AccountRepository accountRepository;
    private final OutageReportRepository outageRepository;

    @Autowired
    public AccountsController(
            Accounts accounts,
            Utilities utilities,
            Facilities facilities,
            AccountRepository accountRepository,
            OutageReportRepository outageRepository) {

        this.accounts = accounts;
        this.utilities = utilities;
        this.facilities = facilities;
        this.accountRepository = accountRepository;
        this.outageRepository = outageRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("applicationName", applicationName);
        return "index";
    }

    @GetMapping("/user/report-outage")
    public String reportOutage(Model model) {
        return "report-outage";
    }

    @PostMapping("/user/report-outage")
    public String reportOutage(
            @RequestParam String datetime,
            @RequestParam String description,
            @AuthenticationPrincipal G6UserPrincipal user
    )
    {
        Report newOutage = new Report(user.getAccountId(), datetime, description);
        outageRepository.save(newOutage);
        return "outage-submit";
    }


    @GetMapping("/admin/outages") String outages(Model model) {
        List<Report> reports = outageRepository.findAll();
        model.addAttribute("reports", reports);
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
    public ModelAndView subscribe(@Valid @ModelAttribute Subscriber subscriber,
                            BindingResult bindingResult, Model model) throws NoSuchAlgorithmException {
        /*
         * (1) Take subscription form input from user.
         * (2) Generate account id.
         * (3) Send activation request to facilities.
         * (4) Confirm to user that activation is pending.
         */
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

        // logs in the user programmatically after registration
        G6UserPrincipal user = new G6UserPrincipal(account, subscriber);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return new ModelAndView("redirect:/password-reset?created");
    }


    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/password-reset")
    public String passwordReset(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("account", accountRepository.findByEmail(user.getUsername()));
        return "password-reset";
    }

    @PostMapping("/password-reset")
    public String passwordReset(@Valid @ModelAttribute Account account,
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


    @GetMapping("/user/account-details")
    public String accountDetails() {
        return "account-details";
    }
}
