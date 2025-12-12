package com.yachtclub.backend.services.base;

import com.yachtclub.backend.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base service implementation providing common CRUD operations.
 * 
 * @param <E>  Entity type
 * @param <D>  DTO type
 * @param <ID> ID type (typically Long)
 */
@Transactional
public abstract class BaseServiceImpl<E, D, ID> {

    /**
     * Get the repository for this service.
     * Must be implemented by concrete services.
     */
    protected abstract JpaRepository<E, ID> getRepository();

    /**
     * Get the mapper for entity-DTO conversion.
     * Must be implemented by concrete services.
     */
    protected abstract BaseMapper<E, D> getMapper();

    /**
     * Get the resource name for error messages.
     * Example: "Boat", "Partner", "Zone"
     */
    protected abstract String getResourceName();

    /**
     * Get all entities as DTOs.
     */
    @Transactional(readOnly = true)
    public List<D> getAll() {
        return getRepository().findAll().stream()
                .map(getMapper()::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get entity by ID as DTO.
     * Throws ResourceNotFoundException if not found (REST best practice).
     * 
     * @param id Entity ID
     * @return DTO
     * @throws ResourceNotFoundException if entity not found
     */
    @Transactional(readOnly = true)
    public D getById(ID id) {
        return getRepository().findById(id)
                .map(getMapper()::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException(
                        getResourceName(), "id", id));
    }

    /**
     * Create a new entity from DTO.
     * 
     * @param dto DTO to create
     * @return Created DTO
     */
    public D create(D dto) {
        E entity = getMapper().toEntity(dto);
        // Force ID to null to ensure creation (INSERT) instead of update
        setEntityId(entity, null);
        E saved = getRepository().save(entity);
        return getMapper().toDTO(saved);
    }

    /**
     * Update an existing entity.
     * Throws ResourceNotFoundException if entity doesn't exist.
     * 
     * @param id  Entity ID
     * @param dto Updated DTO
     * @return Updated DTO
     * @throws ResourceNotFoundException if entity not found
     */
    public D update(ID id, D dto) {
        // Verify entity exists
        if (!getRepository().existsById(id)) {
            throw new ResourceNotFoundException(getResourceName(), "id", id);
        }

        // Convert DTO to entity and set ID
        E entity = getMapper().toEntity(dto);
        setEntityId(entity, id);

        // Save and return
        E updated = getRepository().save(entity);
        return getMapper().toDTO(updated);
    }

    /**
     * Delete an entity by ID.
     * Throws ResourceNotFoundException if entity doesn't exist.
     * 
     * @param id Entity ID
     * @throws ResourceNotFoundException if entity not found
     */
    public void delete(ID id) {
        if (!getRepository().existsById(id)) {
            throw new ResourceNotFoundException(getResourceName(), "id", id);
        }
        getRepository().deleteById(id);
    }

    /**
     * Check if entity exists by ID.
     * 
     * @param id Entity ID
     * @return true if exists, false otherwise
     */
    @Transactional(readOnly = true)
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    /**
     * Set the ID on an entity.
     * This method can be overridden if entity has custom ID setter logic.
     * 
     * @param entity Entity to set ID on
     * @param id     ID to set
     */
    protected abstract void setEntityId(E entity, ID id);
}
