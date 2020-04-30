package edu.depaul.g6.ui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.depaul.g6.billing.service.BillService;


@Service
public class BillingService {
    private BillService service;

    @Autowired
    public BillingService(BillService service) {
        this.service = service;
    }
}
