package edu.depaul.g6.billing.repository;

import java.util.List;
import java.util.Date;
import org.springframework.data.repository.PagingAndSortingRepository;
import edu.depaul.g6.billing.domain.Bill;


public interface BillRepository extends PagingAndSortingRepository<Bill, Long> {
}
