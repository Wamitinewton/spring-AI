package com.newton.spring.controllers;

import com.newton.spring.dto.SoftwareEngineerDto;
import com.newton.spring.mappers.SoftwareEngineerMapper;
import com.newton.spring.services.SoftwareEngineerService;
import com.newton.spring.entities.SoftwareEngineerEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/software-engineers")
public class SoftwareEngineerController {
    private final SoftwareEngineerService service;
    private final SoftwareEngineerMapper mapper;

    public SoftwareEngineerController(SoftwareEngineerService service, SoftwareEngineerMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/all-engineers")
    public ResponseEntity<List<SoftwareEngineerDto>> getEngineers() {
        List<SoftwareEngineerEntity> engineers = service.getSoftwareEngineers();
        return ResponseEntity.ok(mapper.toDtoList(engineers));
    }

    @GetMapping("/engineer/{id}")
    public ResponseEntity<SoftwareEngineerDto> getEngineersById(
            @PathVariable Integer id
    ) {
        SoftwareEngineerEntity engineer = service.getSoftwareEngineerById(id);
        if (engineer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.toDto(engineer));
    }

    @PostMapping("/create-engineer")
    public ResponseEntity<SoftwareEngineerDto> addEngineer(
           @RequestBody SoftwareEngineerDto engineerDto) {
        SoftwareEngineerEntity engineer = mapper.toEntity(engineerDto);
        SoftwareEngineerEntity savedEngineer = service.insertSoftwareEngineer(engineer);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(savedEngineer));
    }

    @PutMapping("/update-engineer/{id}")
    public ResponseEntity<SoftwareEngineerDto> updateEngineer(
            @PathVariable Integer id,
            @RequestBody SoftwareEngineerDto engineerDto
    ) {
        // first checking if the software engineer exists
        SoftwareEngineerEntity existingEngineer = service.getSoftwareEngineerById(id);

        if (existingEngineer == null) {
            return ResponseEntity.notFound().build();
        }

        engineerDto.setId(id);
        SoftwareEngineerEntity updatedEngineer = mapper.updateEntity(engineerDto, existingEngineer);

        updatedEngineer = service.updateSoftwareEngineer(updatedEngineer);

        return ResponseEntity.ok(mapper.toDto(updatedEngineer));
    }

    @DeleteMapping("/delete-engineer/{id}")
    public ResponseEntity<Void> deleteEngineer(@PathVariable Integer id) {
        SoftwareEngineerEntity engineer = service.getSoftwareEngineerById(id);
        if (engineer == null) {
            return ResponseEntity.notFound().build();
        }
        service.deleteSoftwareEngineer(id);
        return ResponseEntity.noContent().build();
    }
}
