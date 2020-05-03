package edu.depaul.g6.facilities.repository;

import edu.depaul.g6.facilities.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
}
