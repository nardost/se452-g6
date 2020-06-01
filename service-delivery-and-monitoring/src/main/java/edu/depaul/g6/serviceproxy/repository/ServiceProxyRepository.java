package edu.depaul.g6.serviceproxy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.depaul.g6.serviceproxy.domain.ServiceProxy;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceProxyRepository extends MongoRepository<ServiceProxy, String> {
}
