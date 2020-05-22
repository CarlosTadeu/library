package com.opussoftware.web.rest;

import com.opussoftware.LibraryApp;
import com.opussoftware.domain.CopyBook;
import com.opussoftware.repository.CopyBookRepository;
import com.opussoftware.service.CopyBookService;
import com.opussoftware.service.dto.CopyBookDTO;
import com.opussoftware.service.mapper.CopyBookMapper;

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
 * Integration tests for the {@link CopyBookResource} REST controller.
 */
@SpringBootTest(classes = LibraryApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class CopyBookResourceIT {

    private static final Boolean DEFAULT_AVAILABLE = false;
    private static final Boolean UPDATED_AVAILABLE = true;

    @Autowired
    private CopyBookRepository copyBookRepository;

    @Autowired
    private CopyBookMapper copyBookMapper;

    @Autowired
    private CopyBookService copyBookService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCopyBookMockMvc;

    private CopyBook copyBook;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CopyBook createEntity(EntityManager em) {
        CopyBook copyBook = new CopyBook()
            .available(DEFAULT_AVAILABLE);
        return copyBook;
    }

    /**
     * Create an updated entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CopyBook createUpdatedEntity(EntityManager em) {
        CopyBook copyBook = new CopyBook()
            .available(UPDATED_AVAILABLE);
        return copyBook;
    }

    @BeforeEach
    public void initTest() {
        copyBook = createEntity(em);
    }

    @Test
    @Transactional
    public void createCopyBook() throws Exception {
        int databaseSizeBeforeCreate = copyBookRepository.findAll().size();

        // Create the CopyBook
        CopyBookDTO copyBookDTO = copyBookMapper.toDto(copyBook);
        restCopyBookMockMvc.perform(post("/api/copy-books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(copyBookDTO)))
            .andExpect(status().isCreated());

        // Validate the CopyBook in the database
        List<CopyBook> copyBookList = copyBookRepository.findAll();
        assertThat(copyBookList).hasSize(databaseSizeBeforeCreate + 1);
        CopyBook testCopyBook = copyBookList.get(copyBookList.size() - 1);
        assertThat(testCopyBook.isAvailable()).isEqualTo(DEFAULT_AVAILABLE);
    }

    @Test
    @Transactional
    public void createCopyBookWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = copyBookRepository.findAll().size();

        // Create the CopyBook with an existing ID
        copyBook.setId(1L);
        CopyBookDTO copyBookDTO = copyBookMapper.toDto(copyBook);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCopyBookMockMvc.perform(post("/api/copy-books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(copyBookDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CopyBook in the database
        List<CopyBook> copyBookList = copyBookRepository.findAll();
        assertThat(copyBookList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCopyBooks() throws Exception {
        // Initialize the database
        copyBookRepository.saveAndFlush(copyBook);

        // Get all the copyBookList
        restCopyBookMockMvc.perform(get("/api/copy-books?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(copyBook.getId().intValue())))
            .andExpect(jsonPath("$.[*].available").value(hasItem(DEFAULT_AVAILABLE.booleanValue())));
    }

    @Test
    @Transactional
    public void getCopyBook() throws Exception {
        // Initialize the database
        copyBookRepository.saveAndFlush(copyBook);

        // Get the copyBook
        restCopyBookMockMvc.perform(get("/api/copy-books/{id}", copyBook.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(copyBook.getId().intValue()))
            .andExpect(jsonPath("$.available").value(DEFAULT_AVAILABLE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCopyBook() throws Exception {
        // Get the copyBook
        restCopyBookMockMvc.perform(get("/api/copy-books/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCopyBook() throws Exception {
        // Initialize the database
        copyBookRepository.saveAndFlush(copyBook);

        int databaseSizeBeforeUpdate = copyBookRepository.findAll().size();

        // Update the copyBook
        CopyBook updatedCopyBook = copyBookRepository.findById(copyBook.getId()).get();
        // Disconnect from session so that the updates on updatedCopyBook are not directly saved in db
        em.detach(updatedCopyBook);
        updatedCopyBook
            .available(UPDATED_AVAILABLE);
        CopyBookDTO copyBookDTO = copyBookMapper.toDto(updatedCopyBook);

        restCopyBookMockMvc.perform(put("/api/copy-books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(copyBookDTO)))
            .andExpect(status().isOk());

        // Validate the CopyBook in the database
        List<CopyBook> copyBookList = copyBookRepository.findAll();
        assertThat(copyBookList).hasSize(databaseSizeBeforeUpdate);
        CopyBook testCopyBook = copyBookList.get(copyBookList.size() - 1);
        assertThat(testCopyBook.isAvailable()).isEqualTo(UPDATED_AVAILABLE);
    }

    @Test
    @Transactional
    public void updateNonExistingCopyBook() throws Exception {
        int databaseSizeBeforeUpdate = copyBookRepository.findAll().size();

        // Create the CopyBook
        CopyBookDTO copyBookDTO = copyBookMapper.toDto(copyBook);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCopyBookMockMvc.perform(put("/api/copy-books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(copyBookDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CopyBook in the database
        List<CopyBook> copyBookList = copyBookRepository.findAll();
        assertThat(copyBookList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCopyBook() throws Exception {
        // Initialize the database
        copyBookRepository.saveAndFlush(copyBook);

        int databaseSizeBeforeDelete = copyBookRepository.findAll().size();

        // Delete the copyBook
        restCopyBookMockMvc.perform(delete("/api/copy-books/{id}", copyBook.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CopyBook> copyBookList = copyBookRepository.findAll();
        assertThat(copyBookList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
