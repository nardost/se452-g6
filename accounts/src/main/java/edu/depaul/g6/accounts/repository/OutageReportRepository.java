package edu.depaul.g6.accounts.repository;

import edu.depaul.g6.accounts.domain.Report;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface OutageReportRepository extends MongoRepository<Report, String> {
}
