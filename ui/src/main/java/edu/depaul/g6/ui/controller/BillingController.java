package edu.depaul.g6.ui.controller;

import java.util.List;

import edu.depaul.g6.billing.service.BillService;
import edu.depaul.g6.ui.config.G6UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.depaul.g6.billing.domain.Bill;
import edu.depaul.g6.billing.repository.BillRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Slf4j
@Controller
public class BillingController {

    private final BillRepository billRepository;
    private final BillService billService;

    @Autowired
    public BillingController(BillRepository billRepository, BillService billService) {
        this.billRepository = billRepository;
        this.billService = billService;
    }


    @GetMapping("/user/bills")
    public String bills(Model model, @AuthenticationPrincipal G6UserPrincipal user) {
        List<Bill> bills = billRepository.findAllByAccountNumber(user.getAccountId());
        model.addAttribute("bills", bills);
        return "bills";
    }


    @GetMapping("/user/pay-bill")
    public String passwordReset(Model model, @RequestParam(defaultValue = "") String id, @AuthenticationPrincipal G6UserPrincipal user) {
        List<Bill> bills = billRepository.findAllByAccountNumber(user.getAccountId());
        bills.removeIf(b -> b.getPaid() || (!id.equals("") && b.getId() == Long.parseLong(id))); // remove paid bills
        model.addAttribute("bills", bills);
        model.addAttribute("select", id);
        return "pay-bill";
    }


    @PostMapping("/user/pay-bill")
    public String payBill(Model model, @RequestParam String id) {
        billService.setBillAsPaid(Long.parseLong(id));
        model.addAttribute("id", id);
        return "bill-paid";
    }
}
