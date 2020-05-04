package edu.depaul.g6.payment.service;

import edu.depaul.g6.payment.domain.PastPayment;
import edu.depaul.g6.payment.repository.PastPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PastPaymentService {

    private PastPaymentRepository pastPaymentRepository;

    @Autowired
    PastPaymentService(PastPaymentRepository pastPaymentRepository){this.pastPaymentRepository = pastPaymentRepository; }

    List<PastPayment> getPastPaymentsById(int customerId) {
        return StreamSupport.stream(pastPaymentRepository.findAllById(customerId).spliterator(), false).collect(Collectors.toList());
    }

}
