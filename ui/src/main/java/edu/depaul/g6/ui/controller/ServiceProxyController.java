package edu.depaul.g6.ui.controller;

import edu.depaul.g6.serviceproxy.domain.Usage;
import edu.depaul.g6.ui.config.PageSize;
import edu.depaul.g6.ui.config.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.depaul.g6.serviceproxy.domain.ServiceProxy;
import edu.depaul.g6.serviceproxy.repository.ServiceProxyRepository;
import edu.depaul.g6.facilities.domain.Subscription;
import edu.depaul.g6.facilities.repository.SubscriptionRepository;
import edu.depaul.g6.ui.config.G6UserPrincipal;
import org.springframework.web.bind.annotation.RequestParam;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
public class ServiceProxyController {

    private final ServiceProxyRepository serviceRepo;
    private final SubscriptionRepository subscriptionRepo;
    @Autowired
    ConversionService conversionService;

    @Autowired
    public ServiceProxyController(
            ServiceProxyRepository proxyRepo,
            SubscriptionRepository subRepo) {

        this.serviceRepo = proxyRepo;
        this.subscriptionRepo = subRepo;
    }

    /** Wrote my own custom paging. I couldn't use Spring's because we have an embedded document... shame.
     */
    @GetMapping("/user/usage")
    public String usage(
            Model model,
            @RequestParam(defaultValue = "10") PageSize pageSize,
            @RequestParam(defaultValue = "0") int page,
            @AuthenticationPrincipal G6UserPrincipal user)
    {
        Subscription subscription = null;
        if(subscriptionRepo.findById(user.getAccountId()).isPresent()) {
            subscription = subscriptionRepo.findById(user.getAccountId()).get();
        }
        ServiceProxy service = null;
        Paging paging = new Paging(0,0,0);
        List<Usage> paginated = new ArrayList<>();
        if(subscription != null &&
                serviceRepo.findById(subscription.getLocation().getMeterMacAddress()).isPresent()) {
            service = serviceRepo.findById(subscription.getLocation().getMeterMacAddress()).get();
        }

        if(service != null) {
            int numPages = getNumPages(service.getUsage().size(), pageSize);
            page = sanitizePage(page, numPages);
            Integer i = conversionService.convert(pageSize, Integer.class);
            i = (i == null) ? 0 : i;
            paging = new Paging(numPages, page, i);
            paginated = getSubList(paging, service.getUsage());
        }

        model.addAttribute("paginated", paginated);
        model.addAttribute("paging", paging);
        return "usage";
    }

    private int getNumPages(int listSize, PageSize pageSize) {
        // divide the list by the page size, round up
        Integer d = conversionService.convert(pageSize, Integer.class);
        d = (d == null) ? 0 : d;
        return (int) Math.ceil(listSize / d.doubleValue());
    }


    private int sanitizePage(int page, int numPages) {
        if (page < 0) page = 0;
        else if (page >= numPages) page = numPages - 1;
        return page;
    }


    private List<Usage> getSubList(Paging paging, List<Usage> usage) {
        int start = startIndex(paging.getCurrentPage(), paging.getPageSize());
        int end = endIndex(paging.getCurrentPage(), paging.getPageSize(), usage.size());
        return usage.subList(start, end); // [start, end)
    }


    // inclusive
    private int startIndex(int page, int pageSize) {
        return page * pageSize;
    }


    // exclusive
    private int endIndex(int page, int pageSize, int listSize) {
        return Math.min(page * pageSize + pageSize, listSize);
    }
}
