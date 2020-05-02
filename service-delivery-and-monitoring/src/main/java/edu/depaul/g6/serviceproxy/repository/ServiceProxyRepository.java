package edu.depaul.g6.serviceproxy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import edu.depaul.g6.serviceproxy.domain.ServiceProxy;

public interface ServiceProxyRepository extends MongoRepository<ServiceProxy, String> {
}
