package edu.depaul.g6.accounts.service;

import edu.depaul.g6.accounts.domain.Report;
import edu.depaul.g6.accounts.domain.UserProfile;
import edu.depaul.g6.accounts.repository.OutageReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportOutage {

    private Integer id;

    public void ReportOutage(Integer id){
    this.id = id;
    }

    public void outageReport(Integer id){
        //User report outage. Be sent to Facilities.
        /*
        Report report = OutageReportRepository.findByUserId("1");
        report.setId(id);
        report.setComment("This is the comment");
        report.save();

         */

    }
}