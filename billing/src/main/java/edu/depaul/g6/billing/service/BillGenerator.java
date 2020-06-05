package edu.depaul.g6.billing.service;

import edu.depaul.g6.billing.domain.Bill;
import edu.depaul.g6.billing.repository.BillRepository;
import edu.depaul.g6.facilities.domain.ServiceCategory;
import edu.depaul.g6.facilities.service.Facilities;
import edu.depaul.g6.serviceproxy.service.ServiceProxyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;


@Slf4j
@Service
public class BillGenerator {
    @Autowired
    ServiceProxyService service;

    @Autowired
    Facilities facilities;

    @Autowired
    BillRepository repo;

    @Scheduled(fixedDelay = 60000) // every min
    void generateBill() {
        Map<String, Integer> usageReport = service.getUsageReport(); // mac address -> kWh since last invocation

        for (String mac : usageReport.keySet()) {
            String accountId;

            try { accountId = facilities.getAccountIdByMacAddress(mac); }
            catch (NullPointerException e) { continue; } // this function becomes an issue when you mock the data

            ServiceCategory category = facilities.getCategory(accountId);

            Bill bill = new Bill();
            bill.setAccountNumber(accountId);
            bill.setPaid(false);
            bill.setAmount((int)(usageReport.get(mac) * (category.getTariff() / 100.00)));
            bill.setBillingDate(LocalDate.now());
            bill.setDueDate(LocalDate.now().plusDays(30));
            repo.save(bill);
        }
    }
}
