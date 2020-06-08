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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;


@Slf4j
@Service
public class BillGenerator {

    private ServiceProxyService service;
    private Facilities facilities;
    private BillRepository repo;
    private final static long BILLING_FREQUENCY = 60000L;

    @Autowired
    public void setServiceProxyService(ServiceProxyService service) {
        this.service = service;
    }

    @Autowired
    public void setFacilities(Facilities facilities) {
        this.facilities = facilities;
    }

    @Autowired
    public void setBillRepository(BillRepository repository) {
        this.repo = repository;
    }

    /*
     * Generates every few seconds just for demonstration.
     * In reality, this would be a cron trigger. For example,
     * At the stroke of midnight on the 21st of every month.
     * @Scheduled(cron = "0 0 0 21 * ?")
     */
    @Scheduled(fixedDelay = BILLING_FREQUENCY)
    void generateBill() {
        Map<String, Integer> usageReport = service.getUsageReport(); // mac address -> kWh since last invocation

        for (String mac : usageReport.keySet()) {
            String accountId;

            try { accountId = facilities.getAccountIdByMacAddress(mac); }
            catch (NullPointerException e) { continue; } // this function becomes an issue when you mock the data

            ServiceCategory category = facilities.getCategory(accountId);

            Bill bill = new Bill();
            bill.setAccountNumber(accountId);
            bill.setPaid(false); // wish there was operator overloading in Java...
            bill.setAmount(new BigDecimal(usageReport.get(mac)).multiply(new BigDecimal(category.getTariff()).divide(new BigDecimal(100.00))));
            bill.setBillingDate(LocalDate.now());
            /*
             * Expire the due date for demonstration.
             */
            //bill.setDueDate(LocalDate.now().minusDays(1));
            bill.setDueDate(LocalDate.now().plusDays(30));
            repo.save(bill);
        }
    }
}
