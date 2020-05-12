package edu.depaul.g6.ui.controller;

import edu.depaul.g6.accounts.service.Accounts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AccountsController {

    @Value("${spring.application.name}")
    private String applicationName;

    private Accounts accounts;

    @Autowired
    public AccountsController(Accounts accounts){
        this.accounts = accounts;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("applicationName", applicationName);
        return "home";
    }

}
