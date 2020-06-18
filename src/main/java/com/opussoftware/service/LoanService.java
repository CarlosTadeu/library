package com.opussoftware.service;

import com.opussoftware.service.dto.CopyBookDTO;
import com.opussoftware.service.dto.LoanCreateDTO;
import com.opussoftware.service.dto.LoanDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.opussoftware.domain.Loan}.
 */
public interface LoanService {

    /**
     * Renew a loan.
     *
     * @param loanDTO the id of the entity.
     * @return the entity.
     */
    LoanDTO renewLoan(LoanDTO loanDTO);

    /**
     * Save a loan.
     *
     * @param loanDTO the entity to save.
     * @return the persisted entity.
     */
    LoanDTO save(LoanDTO loanDTO);

    /**
     * Create a loan.
     *
     * @param loanCreateDTO the entity to create.
     * @return the persisted entity
     */
    LoanDTO create(LoanCreateDTO loanCreateDTO);

    /**
     * Return a loan.
     *
     * @param copyBookDTO the copy book to return.
     * @return the entity
     */
    LoanDTO returnLoan(CopyBookDTO copyBookDTO);

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

    /**
     * Get all the loans of the user.
     *
     * @return the list of entities.
     */
    List<LoanDTO> findAllByUser();
}
