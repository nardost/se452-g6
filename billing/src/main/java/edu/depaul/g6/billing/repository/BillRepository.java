package edu.depaul.g6.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.depaul.g6.billing.domain.Bill;


public interface BillRepository extends JpaRepository<Bill, Long> {
}
