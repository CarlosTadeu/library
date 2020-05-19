package com.opussoftware.web.rest;

import com.opussoftware.LibraryApp;
import com.opussoftware.domain.StudentType;
import com.opussoftware.repository.StudentTypeRepository;
import com.opussoftware.service.StudentTypeService;
import com.opussoftware.service.dto.StudentTypeDTO;
import com.opussoftware.service.mapper.StudentTypeMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link StudentTypeResource} REST controller.
 */
@SpringBootTest(classes = LibraryApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class StudentTypeResourceIT {

    private static final String DEFAULT_STUDENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_STUDENT_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_OF_DAYS_LOAN = 1;
    private static final Integer UPDATED_NUMBER_OF_DAYS_LOAN = 2;

    private static final Integer DEFAULT_NUMBER_OF_DAYS_RENEWAL = 1;
    private static final Integer UPDATED_NUMBER_OF_DAYS_RENEWAL = 2;

    private static final Integer DEFAULT_MAX_BOOKS_ON_LOAN = 1;
    private static final Integer UPDATED_MAX_BOOKS_ON_LOAN = 2;

    private static final Integer DEFAULT_MAX_RENEWAL_NUMBER = 1;
    private static final Integer UPDATED_MAX_RENEWAL_NUMBER = 2;

    @Autowired
    private StudentTypeRepository studentTypeRepository;

    @Autowired
    private StudentTypeMapper studentTypeMapper;

    @Autowired
    private StudentTypeService studentTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStudentTypeMockMvc;

    private StudentType studentType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudentType createEntity(EntityManager em) {
        StudentType studentType = new StudentType()
            .studentType(DEFAULT_STUDENT_TYPE)
            .numberOfDaysLoan(DEFAULT_NUMBER_OF_DAYS_LOAN)
            .numberOfDaysRenewal(DEFAULT_NUMBER_OF_DAYS_RENEWAL)
            .maxBooksOnLoan(DEFAULT_MAX_BOOKS_ON_LOAN)
            .maxRenewalNumber(DEFAULT_MAX_RENEWAL_NUMBER);
        return studentType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static StudentType createUpdatedEntity(EntityManager em) {
        StudentType studentType = new StudentType()
            .studentType(UPDATED_STUDENT_TYPE)
            .numberOfDaysLoan(UPDATED_NUMBER_OF_DAYS_LOAN)
            .numberOfDaysRenewal(UPDATED_NUMBER_OF_DAYS_RENEWAL)
            .maxBooksOnLoan(UPDATED_MAX_BOOKS_ON_LOAN)
            .maxRenewalNumber(UPDATED_MAX_RENEWAL_NUMBER);
        return studentType;
    }

    @BeforeEach
    public void initTest() {
        studentType = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudentType() throws Exception {
        int databaseSizeBeforeCreate = studentTypeRepository.findAll().size();

        // Create the StudentType
        StudentTypeDTO studentTypeDTO = studentTypeMapper.toDto(studentType);
        restStudentTypeMockMvc.perform(post("/api/student-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(studentTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the StudentType in the database
        List<StudentType> studentTypeList = studentTypeRepository.findAll();
        assertThat(studentTypeList).hasSize(databaseSizeBeforeCreate + 1);
        StudentType testStudentType = studentTypeList.get(studentTypeList.size() - 1);
        assertThat(testStudentType.getStudentType()).isEqualTo(DEFAULT_STUDENT_TYPE);
        assertThat(testStudentType.getNumberOfDaysLoan()).isEqualTo(DEFAULT_NUMBER_OF_DAYS_LOAN);
        assertThat(testStudentType.getNumberOfDaysRenewal()).isEqualTo(DEFAULT_NUMBER_OF_DAYS_RENEWAL);
        assertThat(testStudentType.getMaxBooksOnLoan()).isEqualTo(DEFAULT_MAX_BOOKS_ON_LOAN);
        assertThat(testStudentType.getMaxRenewalNumber()).isEqualTo(DEFAULT_MAX_RENEWAL_NUMBER);
    }

    @Test
    @Transactional
    public void createStudentTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentTypeRepository.findAll().size();

        // Create the StudentType with an existing ID
        studentType.setId(1L);
        StudentTypeDTO studentTypeDTO = studentTypeMapper.toDto(studentType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentTypeMockMvc.perform(post("/api/student-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(studentTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentType in the database
        List<StudentType> studentTypeList = studentTypeRepository.findAll();
        assertThat(studentTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllStudentTypes() throws Exception {
        // Initialize the database
        studentTypeRepository.saveAndFlush(studentType);

        // Get all the studentTypeList
        restStudentTypeMockMvc.perform(get("/api/student-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(studentType.getId().intValue())))
            .andExpect(jsonPath("$.[*].studentType").value(hasItem(DEFAULT_STUDENT_TYPE)))
            .andExpect(jsonPath("$.[*].numberOfDaysLoan").value(hasItem(DEFAULT_NUMBER_OF_DAYS_LOAN)))
            .andExpect(jsonPath("$.[*].numberOfDaysRenewal").value(hasItem(DEFAULT_NUMBER_OF_DAYS_RENEWAL)))
            .andExpect(jsonPath("$.[*].maxBooksOnLoan").value(hasItem(DEFAULT_MAX_BOOKS_ON_LOAN)))
            .andExpect(jsonPath("$.[*].maxRenewalNumber").value(hasItem(DEFAULT_MAX_RENEWAL_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getStudentType() throws Exception {
        // Initialize the database
        studentTypeRepository.saveAndFlush(studentType);

        // Get the studentType
        restStudentTypeMockMvc.perform(get("/api/student-types/{id}", studentType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(studentType.getId().intValue()))
            .andExpect(jsonPath("$.studentType").value(DEFAULT_STUDENT_TYPE))
            .andExpect(jsonPath("$.numberOfDaysLoan").value(DEFAULT_NUMBER_OF_DAYS_LOAN))
            .andExpect(jsonPath("$.numberOfDaysRenewal").value(DEFAULT_NUMBER_OF_DAYS_RENEWAL))
            .andExpect(jsonPath("$.maxBooksOnLoan").value(DEFAULT_MAX_BOOKS_ON_LOAN))
            .andExpect(jsonPath("$.maxRenewalNumber").value(DEFAULT_MAX_RENEWAL_NUMBER));
    }

    @Test
    @Transactional
    public void getNonExistingStudentType() throws Exception {
        // Get the studentType
        restStudentTypeMockMvc.perform(get("/api/student-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudentType() throws Exception {
        // Initialize the database
        studentTypeRepository.saveAndFlush(studentType);

        int databaseSizeBeforeUpdate = studentTypeRepository.findAll().size();

        // Update the studentType
        StudentType updatedStudentType = studentTypeRepository.findById(studentType.getId()).get();
        // Disconnect from session so that the updates on updatedStudentType are not directly saved in db
        em.detach(updatedStudentType);
        updatedStudentType
            .studentType(UPDATED_STUDENT_TYPE)
            .numberOfDaysLoan(UPDATED_NUMBER_OF_DAYS_LOAN)
            .numberOfDaysRenewal(UPDATED_NUMBER_OF_DAYS_RENEWAL)
            .maxBooksOnLoan(UPDATED_MAX_BOOKS_ON_LOAN)
            .maxRenewalNumber(UPDATED_MAX_RENEWAL_NUMBER);
        StudentTypeDTO studentTypeDTO = studentTypeMapper.toDto(updatedStudentType);

        restStudentTypeMockMvc.perform(put("/api/student-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(studentTypeDTO)))
            .andExpect(status().isOk());

        // Validate the StudentType in the database
        List<StudentType> studentTypeList = studentTypeRepository.findAll();
        assertThat(studentTypeList).hasSize(databaseSizeBeforeUpdate);
        StudentType testStudentType = studentTypeList.get(studentTypeList.size() - 1);
        assertThat(testStudentType.getStudentType()).isEqualTo(UPDATED_STUDENT_TYPE);
        assertThat(testStudentType.getNumberOfDaysLoan()).isEqualTo(UPDATED_NUMBER_OF_DAYS_LOAN);
        assertThat(testStudentType.getNumberOfDaysRenewal()).isEqualTo(UPDATED_NUMBER_OF_DAYS_RENEWAL);
        assertThat(testStudentType.getMaxBooksOnLoan()).isEqualTo(UPDATED_MAX_BOOKS_ON_LOAN);
        assertThat(testStudentType.getMaxRenewalNumber()).isEqualTo(UPDATED_MAX_RENEWAL_NUMBER);
    }

    @Test
    @Transactional
    public void updateNonExistingStudentType() throws Exception {
        int databaseSizeBeforeUpdate = studentTypeRepository.findAll().size();

        // Create the StudentType
        StudentTypeDTO studentTypeDTO = studentTypeMapper.toDto(studentType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentTypeMockMvc.perform(put("/api/student-types")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(studentTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the StudentType in the database
        List<StudentType> studentTypeList = studentTypeRepository.findAll();
        assertThat(studentTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStudentType() throws Exception {
        // Initialize the database
        studentTypeRepository.saveAndFlush(studentType);

        int databaseSizeBeforeDelete = studentTypeRepository.findAll().size();

        // Delete the studentType
        restStudentTypeMockMvc.perform(delete("/api/student-types/{id}", studentType.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<StudentType> studentTypeList = studentTypeRepository.findAll();
        assertThat(studentTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
