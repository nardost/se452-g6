package edu.depaul.g6.ui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountsController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("applicationName", applicationName);
        return "home";
    }
}
