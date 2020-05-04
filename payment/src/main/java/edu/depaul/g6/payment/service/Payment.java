package edu.depaul.g6.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Payment {

    private PastPaymentService pastPaymentService;


    @Autowired

    public Payment (PastPaymentService pastPaymentService){
        this.pastPaymentService = pastPaymentService;
    }

    public void makePayment(Integer customerId, Integer amount_paid, String credit_card_num) {
        //Check to see if payment went through
        //Log payment by adding payment to past_payments table.
        //Tell billing that payment went through (by fucntion call from billing)
    }

}
