package edu.depaul.g6.accounts.domain;

public class Accounts {

    private String accountNumber = "REU-";
    private int number;
    private String numberString;
    
    
    public void createAcccount(){
        /*
        This will create the initial profile for the user
        */
    }

    //ToDo: Need to find a way to create the first account number if none are in the system. If one is found, increment by one
    public String accountNumberGenerator(){
        number = 0000001;
        numberString = Integer.toString(number);
        System.out.println(accountNumber + numberString);
        return accountNumber + numberString;
    }
}