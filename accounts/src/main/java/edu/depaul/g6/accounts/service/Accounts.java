package edu.depaul.g6.accounts.service;

//Data
import edu.depaul.g6.accounts.domain.Account;
import edu.depaul.g6.accounts.domain.Report;
import edu.depaul.g6.accounts.domain.Subscriber;
import edu.depaul.g6.accounts.repository.UserRepository;
import edu.depaul.g6.accounts.repository.OutageReportRepository;
import edu.depaul.g6.accounts.domain.UserProfile;
import edu.depaul.g6.facilities.domain.ServiceCategory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//persistence
import javax.persistence.*;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;


@Service
public class Accounts {

    private UserRepository userRepository;
    private OutageReportService outageReportService;

    @Autowired
    public Accounts(UserRepository userRepository){
        this.userRepository = userRepository;
    };

    @Autowired
    public void setOutageReportService(OutageReportService service){
        this.outageReportService = service;
    }

    public List<Report> getAllReports() {
        return outageReportService.getAllOutageReports();
    }

    Report getReportByZipCode(Integer zipCode){
        return outageReportService.getReportByZipCode(zipCode);
    }

    public void updateName(String newFullname){

        //user will update name in SQL database
        UserProfile userProfile = userRepository.findByEmail("prebolla15@gmail.com");
        userProfile.setName(newFullname);
        userRepository.save(userProfile);
    }

    public void updatePassword(String newPassword){
        //user will update password in SQL database
        UserProfile userProfile = userRepository.findByEmail("prebolla15@gmail.com");
        userProfile.setPassword(newPassword);
        userRepository.save(userProfile);
    }

    public void updateEmail(String newEmail){
        //user will update email in SQL database
        UserProfile userProfile = userRepository.findByEmail("prebolla15@gmail.com");
        userProfile.setEmail(newEmail);
        userRepository.save(userProfile);
    }

    /*
    public void createAcccount(){

        This will create the initial profile for the user

    }
     */

    private SubscriberService subscriberService;
    private AccountService accountService;

    @Autowired
    public void setSubscriberService(SubscriberService service) {
        this.subscriberService = service;
    }
    @Autowired void setAccountService(AccountService service) {
        this.accountService = service;
    }

    public String saveSubscriber(Subscriber s) throws NoSuchAlgorithmException {
        return subscriberService.saveSubscriber(s);
    }

    public Account getAccount(String accountNumber) {
        return accountService.getAccount(accountNumber);
    }
}