package edu.depaul.g6.accounts.repository;

import edu.depaul.g6.accounts.domain.Report;
import org.springframework.data.repository.CrudRepository;

public interface OutageReportRepository extends CrudRepository<Report, String> {
    Report findById (Integer id);
}
