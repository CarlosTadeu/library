package com.opussoftware.service.impl;

import com.opussoftware.service.LoanService;
import com.opussoftware.domain.Loan;
import com.opussoftware.repository.LoanRepository;
import com.opussoftware.service.dto.LoanDTO;
import com.opussoftware.service.mapper.LoanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Loan}.
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);

    private final LoanRepository loanRepository;

    private final LoanMapper loanMapper;

    public LoanServiceImpl(LoanRepository loanRepository, LoanMapper loanMapper) {
        this.loanRepository = loanRepository;
        this.loanMapper = loanMapper;
    }

    /**
     * Save a loan.
     *
     * @param loanDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LoanDTO save(LoanDTO loanDTO) {
        log.debug("Request to save Loan : {}", loanDTO);
        Loan loan = loanMapper.toEntity(loanDTO);
        loan = loanRepository.save(loan);
        return loanMapper.toDto(loan);
    }

    /**
     * Get all the loans.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<LoanDTO> findAll() {
        log.debug("Request to get all Loans");
        return loanRepository.findAll().stream()
            .map(loanMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one loan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LoanDTO> findOne(Long id) {
        log.debug("Request to get Loan : {}", id);
        return loanRepository.findById(id)
            .map(loanMapper::toDto);
    }

    /**
     * Delete the loan by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Loan : {}", id);
        loanRepository.deleteById(id);
    }
}
