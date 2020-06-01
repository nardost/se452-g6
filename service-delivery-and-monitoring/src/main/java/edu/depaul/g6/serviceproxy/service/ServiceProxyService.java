package edu.depaul.g6.serviceproxy.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.depaul.g6.serviceproxy.domain.Usage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.depaul.g6.serviceproxy.domain.ServiceProxy;
import edu.depaul.g6.serviceproxy.repository.ServiceProxyRepository;


@Service
public class ServiceProxyService {
    private ServiceProxyRepository repo;

    @Autowired
    ServiceProxyService(ServiceProxyRepository repository) {
        this.repo = repository;
    }

    private Instant lastInvocation = null;

    /**
     * @return Map from mac address -> kWh usage since the last invocation.
     */
    public Map<String, Integer> getUsageReport() {
        Map<String, Integer> usageReport = new HashMap<String, Integer>();
        List<ServiceProxy> meters = repo.findAll();

        for (ServiceProxy meter : meters)
            usageReport.put(meter.getId(), usageSince(meter, lastInvocation));

        lastInvocation = Instant.now();

        return usageReport;
    }


    private Integer usageSince(ServiceProxy meter, Instant lastInvocation) {
        List<Usage> usages = meter.getUsage();

        Integer accumulation = 0;

        for (int i = usages.size() - 1; i >= 0; --i) {
            if (lastInvocation != null && Instant.parse(usages.get(i).getFrom()).isBefore(lastInvocation)) break;
            accumulation += usages.get(i).getKwhUsed();
        }

        return accumulation;
    }
}
