package edu.depaul.g6.facilities.repository;

import edu.depaul.g6.facilities.domain.Location;
import edu.depaul.g6.facilities.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {
    Subscription findByLocation(Location location);
}
