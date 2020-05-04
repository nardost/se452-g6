package edu.depaul.g6.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.depaul.g6.serviceproxy.domain.ServiceProxy;
import edu.depaul.g6.serviceproxy.service.ServiceProxyService;


@Controller
public class ServiceProxyController {
    private ServiceProxyService service;

    @Autowired
    public ServiceProxyController(ServiceProxyService service) {
        this.service = service;
    }

    @GetMapping("/usage")
    public String bills(Model model) {
        List<ServiceProxy> meters = service.getAllMeters();
        model.addAttribute("meters", meters);
        return "usage";
    }
}
