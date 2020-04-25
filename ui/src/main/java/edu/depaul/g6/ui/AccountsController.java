package edu.depaul.g6.ui;

import edu.depaul.g6.accounts.service.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
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

        //Todo: Only 2 of the 3 updates can be called at once. 3/3 will result in error page
        //Will update the name of user
        accounts.updateName("Ron Swanson");

        //Will update the email of user
        accounts.updateEmail("newEmail123@gmail.com");

        //Will update the password
        //accounts.updatePassword("newPassword123");

        return "home";
    }
}
