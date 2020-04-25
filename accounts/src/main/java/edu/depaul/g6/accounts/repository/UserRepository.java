package edu.depaul.g6.accounts.repository;

import edu.depaul.g6.accounts.domain.UserProfile;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<UserProfile,String> {
    UserProfile findByEmail(String email);
}
