package edu.depaul.g6.accounts.service;

//Data
import edu.depaul.g6.accounts.domain.Account;
import edu.depaul.g6.accounts.domain.Subscriber;
import edu.depaul.g6.accounts.repository.UserRepository;
import edu.depaul.g6.accounts.domain.UserProfile;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//persistence
import javax.persistence.*;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;


@Service
public class Accounts {

    private UserRepository userRepository;

    @Autowired
    public Accounts(UserRepository userRepository){
        this.userRepository = userRepository;
    };

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
    //TODO This will move out into accounts
    private SubscriberService subscriberService;
    private AccountService accountService;

    //TODO This will move out into accounts
    @Autowired
    public void setSubscriberService(SubscriberService service) {
        this.subscriberService = service;
    }
    @Autowired void setAccountService(AccountService service) {
        this.accountService = service;
    }

    //TODO This will move out into accounts
    public String saveSubscriber(Subscriber s) throws NoSuchAlgorithmException {
        return subscriberService.saveSubscriber(s);
    }

    //TODO This will move out into accounts
    public Account getAccount(String accountNumber) {
        return accountService.getAccount(accountNumber);
    }
}