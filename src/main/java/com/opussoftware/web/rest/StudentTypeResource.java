package com.opussoftware.web.rest;

import com.opussoftware.service.StudentTypeService;
import com.opussoftware.web.rest.errors.BadRequestAlertException;
import com.opussoftware.service.dto.StudentTypeDTO;

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
 * REST controller for managing {@link com.opussoftware.domain.StudentType}.
 */
@RestController
@RequestMapping("/api")
public class StudentTypeResource {

    private final Logger log = LoggerFactory.getLogger(StudentTypeResource.class);

    private static final String ENTITY_NAME = "studentType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StudentTypeService studentTypeService;

    public StudentTypeResource(StudentTypeService studentTypeService) {
        this.studentTypeService = studentTypeService;
    }

    /**
     * {@code POST  /student-types} : Create a new studentType.
     *
     * @param studentTypeDTO the studentTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new studentTypeDTO, or with status {@code 400 (Bad Request)} if the studentType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/student-types")
    public ResponseEntity<StudentTypeDTO> createStudentType(@RequestBody StudentTypeDTO studentTypeDTO) throws URISyntaxException {
        log.debug("REST request to save StudentType : {}", studentTypeDTO);
        if (studentTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new studentType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudentTypeDTO result = studentTypeService.save(studentTypeDTO);
        return ResponseEntity.created(new URI("/api/student-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /student-types} : Updates an existing studentType.
     *
     * @param studentTypeDTO the studentTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated studentTypeDTO,
     * or with status {@code 400 (Bad Request)} if the studentTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the studentTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/student-types")
    public ResponseEntity<StudentTypeDTO> updateStudentType(@RequestBody StudentTypeDTO studentTypeDTO) throws URISyntaxException {
        log.debug("REST request to update StudentType : {}", studentTypeDTO);
        if (studentTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        StudentTypeDTO result = studentTypeService.save(studentTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, studentTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /student-types} : get all the studentTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of studentTypes in body.
     */
    @GetMapping("/student-types")
    public List<StudentTypeDTO> getAllStudentTypes() {
        log.debug("REST request to get all StudentTypes");
        return studentTypeService.findAll();
    }

    /**
     * {@code GET  /student-types/:id} : get the "id" studentType.
     *
     * @param id the id of the studentTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the studentTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/student-types/{id}")
    public ResponseEntity<StudentTypeDTO> getStudentType(@PathVariable Long id) {
        log.debug("REST request to get StudentType : {}", id);
        Optional<StudentTypeDTO> studentTypeDTO = studentTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(studentTypeDTO);
    }

    /**
     * {@code DELETE  /student-types/:id} : delete the "id" studentType.
     *
     * @param id the id of the studentTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/student-types/{id}")
    public ResponseEntity<Void> deleteStudentType(@PathVariable Long id) {
        log.debug("REST request to delete StudentType : {}", id);
        studentTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
