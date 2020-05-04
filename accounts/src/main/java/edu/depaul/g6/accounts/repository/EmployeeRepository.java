package edu.depaul.g6.accounts.repository;

import edu.depaul.g6.accounts.domain.EmployeeProfile;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeProfile,String> {
    EmployeeProfile findByEmail(String email);
}
