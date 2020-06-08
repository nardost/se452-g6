package edu.depaul.g6.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.depaul.g6.billing.domain.Bill;
import edu.depaul.g6.billing.repository.BillRepository;


@Service
public class BillService {
    private final BillRepository repo;

    @Autowired
    BillService(BillRepository repo) {
        this.repo = repo;
    }

    public void setBillAsPaid(Long id) {
        repo.findById(id).ifPresent((Bill bill) -> {
            bill.setPaid(true);
            repo.save(bill);
        });
    }
}
