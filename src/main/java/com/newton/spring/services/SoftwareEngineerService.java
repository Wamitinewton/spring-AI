package com.newton.spring.services;

import com.newton.spring.repository.SoftwareEngineerRepository;
import com.newton.spring.entities.SoftwareEngineerEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareEngineerService {

    private final SoftwareEngineerRepository repository;

    public SoftwareEngineerService(SoftwareEngineerRepository repository) {
        this.repository = repository;
    }

    public List<SoftwareEngineerEntity> getSoftwareEngineers() {
        return repository.findAll();
    }

    public SoftwareEngineerEntity insertSoftwareEngineer(
            SoftwareEngineerEntity engineer) {
        // Setting ID to null to ensure it's a new entity and not an update
        engineer.setId(null);
        return repository.save(engineer);
    }

    public SoftwareEngineerEntity getSoftwareEngineerById(Integer id) {
        return repository.findById(id).orElseThrow(() ->
                new IllegalStateException(id + " not found"));
    }

    public void deleteSoftwareEngineer(Integer id) {
        repository.deleteById(id);
    }

    public SoftwareEngineerEntity updateSoftwareEngineer(SoftwareEngineerEntity engineer){
        return repository.save(engineer);
    }
}
