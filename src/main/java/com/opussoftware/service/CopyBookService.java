package com.opussoftware.service;

import com.opussoftware.service.dto.CopyBookDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.opussoftware.domain.CopyBook}.
 */
public interface CopyBookService {

    /**
     * Save a copyBook.
     *
     * @param copyBookDTO the entity to save.
     * @return the persisted entity.
     */
    CopyBookDTO save(CopyBookDTO copyBookDTO);

    /**
     * Get all the copyBooks.
     *
     * @return the list of entities.
     */
    List<CopyBookDTO> findAll();

    /**
     * Get the "id" copyBook.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CopyBookDTO> findOne(Long id);

    /**
     * Delete the "id" copyBook.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
