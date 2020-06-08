package edu.depaul.g6.payment.service;

import edu.depaul.g6.payment.domain.PastPayment;
import edu.depaul.g6.payment.repository.PastPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PastPaymentService {

    private final PastPaymentRepository pastPaymentRepository;

    @Autowired
    public PastPaymentService(PastPaymentRepository pastPaymentRepository) {
        this.pastPaymentRepository = pastPaymentRepository;
    }

    List<PastPayment> getPastPaymentsById(int customerId) {
        return new ArrayList<>(pastPaymentRepository.findAllById(customerId));
    }

}
