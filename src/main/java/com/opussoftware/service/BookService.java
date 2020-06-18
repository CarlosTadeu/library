package com.opussoftware.service;

import com.opussoftware.service.dto.BookDTO;

import com.opussoftware.service.dto.SearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.opussoftware.domain.Book}.
 */
public interface BookService {

    /**
     * Create a book.
     *
     * @param bookDTO the entity to save.
     * @return the persisted entity.
     */
    BookDTO create(BookDTO bookDTO);

    /**
     * Save a book.
     *
     * @param bookDTO the entity to save.
     * @return the persisted entity.
     */
    BookDTO save(BookDTO bookDTO);

    /**
     * Get all the books.
     *
     * @return the list of entities.
     */
    List<BookDTO> findAll();

    /**
     * Get all the books with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<BookDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" book.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BookDTO> findOne(Long id);

    /**
     * Delete the "id" book.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Get all the books with option search.
     *
     * @return the list of entities.
     */
    List<BookDTO> search(SearchDTO searchDTO);
}
