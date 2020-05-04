package edu.depaul.g6.opms.repository;

import edu.depaul.g6.opms.domain.EnergyUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnergyUsageRipository extends JpaRepository<EnergyUsage, Integer > {

    List<EnergyUsage> findById(int id);
}
