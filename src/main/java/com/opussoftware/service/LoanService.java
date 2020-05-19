package com.opussoftware.service;

import com.opussoftware.service.dto.LoanDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.opussoftware.domain.Loan}.
 */
public interface LoanService {

    /**
     * Save a loan.
     *
     * @param loanDTO the entity to save.
     * @return the persisted entity.
     */
    LoanDTO save(LoanDTO loanDTO);

    /**
     * Get all the loans.
     *
     * @return the list of entities.
     */
    List<LoanDTO> findAll();

    /**
     * Get the "id" loan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LoanDTO> findOne(Long id);

    /**
     * Delete the "id" loan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
