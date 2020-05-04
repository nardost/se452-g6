package edu.depaul.g6.payment.repository;

import edu.depaul.g6.payment.domain.PastPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PastPaymentRepository extends JpaRepository<PastPayment, Integer> {
   List<PastPayment> findAllById(int customerId);
}
