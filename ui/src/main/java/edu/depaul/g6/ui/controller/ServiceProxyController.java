package edu.depaul.g6.ui.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.depaul.g6.serviceproxy.domain.ServiceProxy;
import edu.depaul.g6.serviceproxy.repository.ServiceProxyRepository;
import edu.depaul.g6.facilities.domain.Subscription;
import edu.depaul.g6.facilities.repository.SubscriptionRepository;
import edu.depaul.g6.ui.config.G6UserPrincipal;


@Slf4j
@Controller
public class ServiceProxyController {
    @Autowired
    private ServiceProxyRepository serviceRepo;

    @Autowired
    private SubscriptionRepository subscriptionRepo;

    @GetMapping("/user/usage")
    public String usage(Model model, @AuthenticationPrincipal G6UserPrincipal user) {
        Subscription subscription = subscriptionRepo.findById(user.getAccountId()).get();
        ServiceProxy service = serviceRepo.findById(subscription.getLocation().getMeterMacAddress()).get();
        model.addAttribute("meter", service);
        return "usage";
    }
}
