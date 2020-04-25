package edu.depaul.g6.ui.controller;

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

<<<<<<< HEAD
        //Todo: Only 2 of the 3 updates can be called at once. 3/3 will result in error page
=======
>>>>>>> updated the accounts treee & created the updateUserName, updateEmail, updatPassword methods
        //Will update the name of user
        accounts.updateName("Ron Swanson");

        //Will update the email of user
        accounts.updateEmail("newEmail123@gmail.com");

        //Will update the password
<<<<<<< HEAD
        //accounts.updatePassword("newPassword123");

=======
        accounts.updatePassword("parksAndRec@gmail.com");
>>>>>>> updated the accounts treee & created the updateUserName, updateEmail, updatPassword methods
        return "home";
    }
}
