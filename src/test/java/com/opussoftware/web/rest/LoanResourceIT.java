package com.opussoftware.web.rest;

import com.opussoftware.LibraryApp;
import com.opussoftware.domain.Loan;
import com.opussoftware.repository.LoanRepository;
import com.opussoftware.service.LoanService;
import com.opussoftware.service.dto.LoanDTO;
import com.opussoftware.service.mapper.LoanMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LoanResource} REST controller.
 */
@SpringBootTest(classes = LibraryApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LoanResourceIT {

    private static final LocalDate DEFAULT_LOAN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LOAN_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_RETURNED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RETURNED = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO_BE_RETURNED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO_BE_RETURNED = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NUMBER_OF_RENEWALS = 1;
    private static final Integer UPDATED_NUMBER_OF_RENEWALS = 2;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanMapper loanMapper;

    @Autowired
    private LoanService loanService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLoanMockMvc;

    private Loan loan;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Loan createEntity(EntityManager em) {
        Loan loan = new Loan()
            .loanDate(DEFAULT_LOAN_DATE)
            .dateReturned(DEFAULT_DATE_RETURNED)
            .dateToBeReturned(DEFAULT_DATE_TO_BE_RETURNED)
            .numberOfRenewals(DEFAULT_NUMBER_OF_RENEWALS);
        return loan;
    }

    /**
     * Create an updated entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Loan createUpdatedEntity(EntityManager em) {
        Loan loan = new Loan()
            .loanDate(UPDATED_LOAN_DATE)
            .dateReturned(UPDATED_DATE_RETURNED)
            .dateToBeReturned(UPDATED_DATE_TO_BE_RETURNED)
            .numberOfRenewals(UPDATED_NUMBER_OF_RENEWALS);
        return loan;
    }

    @BeforeEach
    public void initTest() {
        loan = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoan() throws Exception {
        int databaseSizeBeforeCreate = loanRepository.findAll().size();

        // Create the Loan
        LoanDTO loanDTO = loanMapper.toDto(loan);
        restLoanMockMvc.perform(post("/api/loans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loanDTO)))
            .andExpect(status().isCreated());

        // Validate the Loan in the database
        List<Loan> loanList = loanRepository.findAll();
        assertThat(loanList).hasSize(databaseSizeBeforeCreate + 1);
        Loan testLoan = loanList.get(loanList.size() - 1);
        assertThat(testLoan.getLoanDate()).isEqualTo(DEFAULT_LOAN_DATE);
        assertThat(testLoan.getDateReturned()).isEqualTo(DEFAULT_DATE_RETURNED);
        assertThat(testLoan.getDateToBeReturned()).isEqualTo(DEFAULT_DATE_TO_BE_RETURNED);
        assertThat(testLoan.getNumberOfRenewals()).isEqualTo(DEFAULT_NUMBER_OF_RENEWALS);
    }

    @Test
    @Transactional
    public void createLoanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loanRepository.findAll().size();

        // Create the Loan with an existing ID
        loan.setId(1L);
        LoanDTO loanDTO = loanMapper.toDto(loan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoanMockMvc.perform(post("/api/loans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Loan in the database
        List<Loan> loanList = loanRepository.findAll();
        assertThat(loanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLoans() throws Exception {
        // Initialize the database
        loanRepository.saveAndFlush(loan);

        // Get all the loanList
        restLoanMockMvc.perform(get("/api/loans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loan.getId().intValue())))
            .andExpect(jsonPath("$.[*].loanDate").value(hasItem(DEFAULT_LOAN_DATE.toString())))
            .andExpect(jsonPath("$.[*].dateReturned").value(hasItem(DEFAULT_DATE_RETURNED.toString())))
            .andExpect(jsonPath("$.[*].dateToBeReturned").value(hasItem(DEFAULT_DATE_TO_BE_RETURNED.toString())))
            .andExpect(jsonPath("$.[*].numberOfRenewals").value(hasItem(DEFAULT_NUMBER_OF_RENEWALS)));
    }

    @Test
    @Transactional
    public void getLoan() throws Exception {
        // Initialize the database
        loanRepository.saveAndFlush(loan);

        // Get the loan
        restLoanMockMvc.perform(get("/api/loans/{id}", loan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(loan.getId().intValue()))
            .andExpect(jsonPath("$.loanDate").value(DEFAULT_LOAN_DATE.toString()))
            .andExpect(jsonPath("$.dateReturned").value(DEFAULT_DATE_RETURNED.toString()))
            .andExpect(jsonPath("$.dateToBeReturned").value(DEFAULT_DATE_TO_BE_RETURNED.toString()))
            .andExpect(jsonPath("$.numberOfRenewals").value(DEFAULT_NUMBER_OF_RENEWALS));
    }

    @Test
    @Transactional
    public void getNonExistingLoan() throws Exception {
        // Get the loan
        restLoanMockMvc.perform(get("/api/loans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoan() throws Exception {
        // Initialize the database
        loanRepository.saveAndFlush(loan);

        int databaseSizeBeforeUpdate = loanRepository.findAll().size();

        // Update the loan
        Loan updatedLoan = loanRepository.findById(loan.getId()).get();
        // Disconnect from session so that the updates on updatedLoan are not directly saved in db
        em.detach(updatedLoan);
        updatedLoan
            .loanDate(UPDATED_LOAN_DATE)
            .dateReturned(UPDATED_DATE_RETURNED)
            .dateToBeReturned(UPDATED_DATE_TO_BE_RETURNED)
            .numberOfRenewals(UPDATED_NUMBER_OF_RENEWALS);
        LoanDTO loanDTO = loanMapper.toDto(updatedLoan);

        restLoanMockMvc.perform(put("/api/loans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loanDTO)))
            .andExpect(status().isOk());

        // Validate the Loan in the database
        List<Loan> loanList = loanRepository.findAll();
        assertThat(loanList).hasSize(databaseSizeBeforeUpdate);
        Loan testLoan = loanList.get(loanList.size() - 1);
        assertThat(testLoan.getLoanDate()).isEqualTo(UPDATED_LOAN_DATE);
        assertThat(testLoan.getDateReturned()).isEqualTo(UPDATED_DATE_RETURNED);
        assertThat(testLoan.getDateToBeReturned()).isEqualTo(UPDATED_DATE_TO_BE_RETURNED);
        assertThat(testLoan.getNumberOfRenewals()).isEqualTo(UPDATED_NUMBER_OF_RENEWALS);
    }

    @Test
    @Transactional
    public void updateNonExistingLoan() throws Exception {
        int databaseSizeBeforeUpdate = loanRepository.findAll().size();

        // Create the Loan
        LoanDTO loanDTO = loanMapper.toDto(loan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLoanMockMvc.perform(put("/api/loans")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(loanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Loan in the database
        List<Loan> loanList = loanRepository.findAll();
        assertThat(loanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLoan() throws Exception {
        // Initialize the database
        loanRepository.saveAndFlush(loan);

        int databaseSizeBeforeDelete = loanRepository.findAll().size();

        // Delete the loan
        restLoanMockMvc.perform(delete("/api/loans/{id}", loan.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Loan> loanList = loanRepository.findAll();
        assertThat(loanList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
