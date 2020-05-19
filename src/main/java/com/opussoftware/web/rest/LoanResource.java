package com.opussoftware.web.rest;

import com.opussoftware.service.LoanService;
import com.opussoftware.web.rest.errors.BadRequestAlertException;
import com.opussoftware.service.dto.LoanDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.opussoftware.domain.Loan}.
 */
@RestController
@RequestMapping("/api")
public class LoanResource {

    private final Logger log = LoggerFactory.getLogger(LoanResource.class);

    private static final String ENTITY_NAME = "loan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoanService loanService;

    public LoanResource(LoanService loanService) {
        this.loanService = loanService;
    }

    /**
     * {@code POST  /loans} : Create a new loan.
     *
     * @param loanDTO the loanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new loanDTO, or with status {@code 400 (Bad Request)} if the loan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/loans")
    public ResponseEntity<LoanDTO> createLoan(@RequestBody LoanDTO loanDTO) throws URISyntaxException {
        log.debug("REST request to save Loan : {}", loanDTO);
        if (loanDTO.getId() != null) {
            throw new BadRequestAlertException("A new loan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LoanDTO result = loanService.save(loanDTO);
        return ResponseEntity.created(new URI("/api/loans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /loans} : Updates an existing loan.
     *
     * @param loanDTO the loanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loanDTO,
     * or with status {@code 400 (Bad Request)} if the loanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/loans")
    public ResponseEntity<LoanDTO> updateLoan(@RequestBody LoanDTO loanDTO) throws URISyntaxException {
        log.debug("REST request to update Loan : {}", loanDTO);
        if (loanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LoanDTO result = loanService.save(loanDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, loanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /loans} : get all the loans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loans in body.
     */
    @GetMapping("/loans")
    public List<LoanDTO> getAllLoans() {
        log.debug("REST request to get all Loans");
        return loanService.findAll();
    }

    /**
     * {@code GET  /loans/:id} : get the "id" loan.
     *
     * @param id the id of the loanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/loans/{id}")
    public ResponseEntity<LoanDTO> getLoan(@PathVariable Long id) {
        log.debug("REST request to get Loan : {}", id);
        Optional<LoanDTO> loanDTO = loanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loanDTO);
    }

    /**
     * {@code DELETE  /loans/:id} : delete the "id" loan.
     *
     * @param id the id of the loanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        log.debug("REST request to delete Loan : {}", id);
        loanService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
