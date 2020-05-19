package com.opussoftware.service;

import com.opussoftware.service.dto.StudentTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.opussoftware.domain.StudentType}.
 */
public interface StudentTypeService {

    /**
     * Save a studentType.
     *
     * @param studentTypeDTO the entity to save.
     * @return the persisted entity.
     */
    StudentTypeDTO save(StudentTypeDTO studentTypeDTO);

    /**
     * Get all the studentTypes.
     *
     * @return the list of entities.
     */
    List<StudentTypeDTO> findAll();

    /**
     * Get the "id" studentType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<StudentTypeDTO> findOne(Long id);

    /**
     * Delete the "id" studentType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
