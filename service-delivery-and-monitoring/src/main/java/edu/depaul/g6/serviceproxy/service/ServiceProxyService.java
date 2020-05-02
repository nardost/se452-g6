package edu.depaul.g6.serviceproxy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.depaul.g6.serviceproxy.domain.ServiceProxy;
import edu.depaul.g6.serviceproxy.repository.ServiceProxyRepository;


@Service
public class ServiceProxyService {
    private ServiceProxyRepository repo;

    @Autowired
    ServiceProxyService(ServiceProxyRepository repository) {
        this.repo = repository;
    }

    public ServiceProxy getMeter(String id) {
        return repo.findById(id).get();
    }
    public List<ServiceProxy> getAllMeters() {
        return repo.findAll();
    }
}
