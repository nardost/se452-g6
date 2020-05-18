package edu.depaul.g6.billing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.depaul.g6.billing.domain.Bill;

import java.util.List;


public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByUtilityId(String account); // user can have 0+ bills
}
