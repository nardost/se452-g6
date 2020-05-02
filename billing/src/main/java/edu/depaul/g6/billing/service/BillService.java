package edu.depaul.g6.billing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.depaul.g6.billing.domain.Bill;
import edu.depaul.g6.billing.repository.BillRepository;


@Service
public class BillService {
    private BillRepository repo;

    @Autowired
    BillService(BillRepository repo) {
        this.repo = repo;
    }
    public List<Bill> getAllBills() {
        return repo.findAll();
    }
}
