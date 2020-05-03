package edu.depaul.g6.opms.service;

import edu.depaul.g6.opms.repository.EnergyUsageRipository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnergyUsageService {

    private EnergyUsageRipository energyUsageRipository;

    @Autowired
    private EnergyUsageService() {

    }

}
