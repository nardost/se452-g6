package edu.depaul.g6.accounts.service;

//Data
import edu.depaul.g6.accounts.repository.UserRepository;
import edu.depaul.g6.accounts.domain.UserProfile;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//persistence
import javax.persistence.*;

import java.io.Serializable;


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

}