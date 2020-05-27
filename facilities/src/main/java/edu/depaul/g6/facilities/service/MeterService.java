package edu.depaul.g6.facilities.service;

import edu.depaul.g6.facilities.domain.Meter;
import edu.depaul.g6.facilities.repository.MeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeterService {

    private final MeterRepository meterRepository;

    @Autowired
    MeterService(MeterRepository repository) {
        this.meterRepository = repository;
    }

    /*
     * Get a meter that is not installed yet.
     * Mark it as installed and return it.
     */
    Meter getMeter() {
        Meter meter = meterRepository.findFirstByInstalledIsFalse();
        meter.setInstalled(true);
        meterRepository.save(meter);
        return meter;
    }
}
