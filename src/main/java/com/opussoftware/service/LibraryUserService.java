package com.opussoftware.service;

import com.opussoftware.service.dto.LibraryUserDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.opussoftware.domain.LibraryUser}.
 */
public interface LibraryUserService {

    /**
     * Save a libraryUser.
     *
     * @param libraryUserDTO the entity to save.
     * @return the persisted entity.
     */
    LibraryUserDTO save(LibraryUserDTO libraryUserDTO);

    /**
     * Get all the libraryUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LibraryUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" libraryUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LibraryUserDTO> findOne(Long id);

    /**
     * Delete the "id" libraryUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
