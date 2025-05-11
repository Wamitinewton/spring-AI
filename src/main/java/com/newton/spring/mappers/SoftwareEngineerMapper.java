package com.newton.spring.mappers;

import com.newton.spring.dto.SoftwareEngineerDto;
import com.newton.spring.entities.SoftwareEngineerEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SoftwareEngineerMapper {

    /**
     * Coverts a SoftwareEngineer entity to a DTO
     *
     * @param entity the entity to convert
     * @return the corresponding DTO
     */

    public SoftwareEngineerDto toDto(SoftwareEngineerEntity entity) {
        if (entity == null) {
            return null;
        }
        return new SoftwareEngineerDto(
                entity.getId(),
                entity.getName(),
                entity.getTechStack()
        );
    }

    /**
     * Converts a list of SoftwareEngineer entities to a list of DTOs
     *
     * @param entities the list of entities to convert
     * @return the list of corresponding DTOs
     */

    public List<SoftwareEngineerDto> toDtoList(List<SoftwareEngineerEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a SoftwareEngineerDTO to a SoftwareEngineer entity
     *
     * @param dto the DTO to convert
     * @return the corresponding entity
     */
    public SoftwareEngineerEntity toEntity(SoftwareEngineerDto dto) {
        if (dto == null) {
            return null;
        }

        return new SoftwareEngineerEntity(
                dto.getId(),
                dto.getName(),
                dto.getTechStack()
        );
    }

    /**
     * Updates an existing entity with data from a DTO
     *
     * @param dto the DTO containing the new data
     * @param entity the entity to update
     * @return the updated entity
     */

    public SoftwareEngineerEntity updateEntity(SoftwareEngineerDto dto, SoftwareEngineerEntity entity) {
        if (dto == null || entity == null) {
            return null;
        }

        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }

        if (dto.getTechStack() != null) {
            entity.setTechStack(dto.getTechStack());
        }
        return entity;
    }
}
