package com.opussoftware.web.rest;

import com.opussoftware.LibraryApp;
import com.opussoftware.domain.LibraryUser;
import com.opussoftware.repository.LibraryUserRepository;
import com.opussoftware.service.LibraryUserService;
import com.opussoftware.service.dto.LibraryUserDTO;
import com.opussoftware.service.mapper.LibraryUserMapper;

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
 * Integration tests for the {@link LibraryUserResource} REST controller.
 */
@SpringBootTest(classes = LibraryApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LibraryUserResourceIT {

    private static final String DEFAULT_CPF = "AAAAAAAAAA";
    private static final String UPDATED_CPF = "BBBBBBBBBB";

    private static final String DEFAULT_RG = "AAAAAAAAAA";
    private static final String UPDATED_RG = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SUSPENSION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUSPENSION_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    private LibraryUserMapper libraryUserMapper;

    @Autowired
    private LibraryUserService libraryUserService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLibraryUserMockMvc;

    private LibraryUser libraryUser;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LibraryUser createEntity(EntityManager em) {
        LibraryUser libraryUser = new LibraryUser()
            .cpf(DEFAULT_CPF)
            .rg(DEFAULT_RG)
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .email(DEFAULT_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .suspensionDate(DEFAULT_SUSPENSION_DATE);
        return libraryUser;
    }

    /**
     * Create an updated entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LibraryUser createUpdatedEntity(EntityManager em) {
        LibraryUser libraryUser = new LibraryUser()
            .cpf(UPDATED_CPF)
            .rg(UPDATED_RG)
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .suspensionDate(UPDATED_SUSPENSION_DATE);
        return libraryUser;
    }

    @BeforeEach
    public void initTest() {
        libraryUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createLibraryUser() throws Exception {
        int databaseSizeBeforeCreate = libraryUserRepository.findAll().size();

        // Create the LibraryUser
        LibraryUserDTO libraryUserDTO = libraryUserMapper.toDto(libraryUser);
        restLibraryUserMockMvc.perform(post("/api/library-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libraryUserDTO)))
            .andExpect(status().isCreated());

        // Validate the LibraryUser in the database
        List<LibraryUser> libraryUserList = libraryUserRepository.findAll();
        assertThat(libraryUserList).hasSize(databaseSizeBeforeCreate + 1);
        LibraryUser testLibraryUser = libraryUserList.get(libraryUserList.size() - 1);
        assertThat(testLibraryUser.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testLibraryUser.getRg()).isEqualTo(DEFAULT_RG);
        assertThat(testLibraryUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLibraryUser.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testLibraryUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testLibraryUser.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testLibraryUser.getSuspensionDate()).isEqualTo(DEFAULT_SUSPENSION_DATE);
    }

    @Test
    @Transactional
    public void createLibraryUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = libraryUserRepository.findAll().size();

        // Create the LibraryUser with an existing ID
        libraryUser.setId(1L);
        LibraryUserDTO libraryUserDTO = libraryUserMapper.toDto(libraryUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLibraryUserMockMvc.perform(post("/api/library-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libraryUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LibraryUser in the database
        List<LibraryUser> libraryUserList = libraryUserRepository.findAll();
        assertThat(libraryUserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLibraryUsers() throws Exception {
        // Initialize the database
        libraryUserRepository.saveAndFlush(libraryUser);

        // Get all the libraryUserList
        restLibraryUserMockMvc.perform(get("/api/library-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(libraryUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].rg").value(hasItem(DEFAULT_RG)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].suspensionDate").value(hasItem(DEFAULT_SUSPENSION_DATE.toString())));
    }

    @Test
    @Transactional
    public void getLibraryUser() throws Exception {
        // Initialize the database
        libraryUserRepository.saveAndFlush(libraryUser);

        // Get the libraryUser
        restLibraryUserMockMvc.perform(get("/api/library-users/{id}", libraryUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(libraryUser.getId().intValue()))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF))
            .andExpect(jsonPath("$.rg").value(DEFAULT_RG))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.suspensionDate").value(DEFAULT_SUSPENSION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLibraryUser() throws Exception {
        // Get the libraryUser
        restLibraryUserMockMvc.perform(get("/api/library-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLibraryUser() throws Exception {
        // Initialize the database
        libraryUserRepository.saveAndFlush(libraryUser);

        int databaseSizeBeforeUpdate = libraryUserRepository.findAll().size();

        // Update the libraryUser
        LibraryUser updatedLibraryUser = libraryUserRepository.findById(libraryUser.getId()).get();
        // Disconnect from session so that the updates on updatedLibraryUser are not directly saved in db
        em.detach(updatedLibraryUser);
        updatedLibraryUser
            .cpf(UPDATED_CPF)
            .rg(UPDATED_RG)
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .email(UPDATED_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .suspensionDate(UPDATED_SUSPENSION_DATE);
        LibraryUserDTO libraryUserDTO = libraryUserMapper.toDto(updatedLibraryUser);

        restLibraryUserMockMvc.perform(put("/api/library-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libraryUserDTO)))
            .andExpect(status().isOk());

        // Validate the LibraryUser in the database
        List<LibraryUser> libraryUserList = libraryUserRepository.findAll();
        assertThat(libraryUserList).hasSize(databaseSizeBeforeUpdate);
        LibraryUser testLibraryUser = libraryUserList.get(libraryUserList.size() - 1);
        assertThat(testLibraryUser.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testLibraryUser.getRg()).isEqualTo(UPDATED_RG);
        assertThat(testLibraryUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLibraryUser.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testLibraryUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testLibraryUser.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testLibraryUser.getSuspensionDate()).isEqualTo(UPDATED_SUSPENSION_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingLibraryUser() throws Exception {
        int databaseSizeBeforeUpdate = libraryUserRepository.findAll().size();

        // Create the LibraryUser
        LibraryUserDTO libraryUserDTO = libraryUserMapper.toDto(libraryUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLibraryUserMockMvc.perform(put("/api/library-users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(libraryUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LibraryUser in the database
        List<LibraryUser> libraryUserList = libraryUserRepository.findAll();
        assertThat(libraryUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLibraryUser() throws Exception {
        // Initialize the database
        libraryUserRepository.saveAndFlush(libraryUser);

        int databaseSizeBeforeDelete = libraryUserRepository.findAll().size();

        // Delete the libraryUser
        restLibraryUserMockMvc.perform(delete("/api/library-users/{id}", libraryUser.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LibraryUser> libraryUserList = libraryUserRepository.findAll();
        assertThat(libraryUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
