package edu.depaul.g6.accounts.repository;

import edu.depaul.g6.accounts.domain.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OutageReportRepository extends MongoRepository<Report, Integer>{
//public interface OutageReportRepository extends CrudRepository<Report, Integer> {
    //Report findById (Integer zipCode);
    List<Report> findByZipCode(Integer zipCode);
}
