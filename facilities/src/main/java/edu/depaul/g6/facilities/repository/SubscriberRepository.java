package edu.depaul.g6.facilities.repository;

import edu.depaul.g6.facilities.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, String> {
}
