package edu.depaul.g6.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.depaul.g6.billing.domain.Bill;
import edu.depaul.g6.billing.service.BillService;


@Controller
public class BillingController {
    private BillService service;

    @Autowired
    public BillingController(BillService service) {
        this.service = service;
    }

    @GetMapping("/bills")
    public String bills(Model model) {
        List<Bill> bills = service.getAllBills();
        model.addAttribute("bills", bills);
        return "bills";
    }
}
