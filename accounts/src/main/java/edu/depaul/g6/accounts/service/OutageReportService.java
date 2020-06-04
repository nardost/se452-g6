package edu.depaul.g6.accounts.service;

import edu.depaul.g6.accounts.domain.Report;
import edu.depaul.g6.accounts.domain.UserProfile;
import edu.depaul.g6.accounts.repository.OutageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutageReportService {

    private final OutageReportRepository outageReportRepository;

    @Autowired
    OutageReportService(OutageReportRepository repository){
        this.outageReportRepository = repository;
    }

    Report getReportByZipCode(Integer zipCode){
        return outageReportRepository.findByZipCode(zipCode).get(zipCode);
    }

    List<Report> getAllOutageReports(){
        return outageReportRepository.findAll();
    }

}