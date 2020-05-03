package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.Meter;
import edu.depaul.g6.facilities.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterService {

    private MeterRepository meterRepository;

    @Autowired
    public MeterService(MeterRepository repository) {
        this.meterRepository = repository;
    }

    public List<Meter> getAllMeters() {
        return meterRepository.findAll();
    }
}
