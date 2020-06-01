package edu.depaul.g6.ui.controller;

import java.util.List;

import edu.depaul.g6.ui.config.G6UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.depaul.g6.billing.domain.Bill;
import edu.depaul.g6.billing.repository.BillRepository;


@Controller
public class BillingController {
    @Autowired
    BillRepository repo;

    @GetMapping("/bills")
    public String bills(Model model) {
        G6UserPrincipal user =
                (G6UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Bill> bills = repo.findAllByAccountNumber(user.getAccountId());
        model.addAttribute("bills", bills);
        return "bills";
    }
}
