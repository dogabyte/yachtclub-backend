package com.yachtclub.backend.services.base;

/**
 * Base mapper interface for entity-DTO conversion.
 * All MapStruct mappers should extend this interface.
 * 
 * @param <E> Entity type
 * @param <D> DTO type
 */
public interface BaseMapper<E, D> {

    /**
     * Convert entity to DTO.
     */
    D toDTO(E entity);

    /**
     * Convert DTO to entity.
     */
    E toEntity(D dto);
}
