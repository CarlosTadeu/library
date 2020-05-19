package com.opussoftware.web.rest;

import com.opussoftware.service.CopyBookService;
import com.opussoftware.web.rest.errors.BadRequestAlertException;
import com.opussoftware.service.dto.CopyBookDTO;

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
 * REST controller for managing {@link com.opussoftware.domain.CopyBook}.
 */
@RestController
@RequestMapping("/api")
public class CopyBookResource {

    private final Logger log = LoggerFactory.getLogger(CopyBookResource.class);

    private static final String ENTITY_NAME = "copyBook";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CopyBookService copyBookService;

    public CopyBookResource(CopyBookService copyBookService) {
        this.copyBookService = copyBookService;
    }

    /**
     * {@code POST  /copy-books} : Create a new copyBook.
     *
     * @param copyBookDTO the copyBookDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new copyBookDTO, or with status {@code 400 (Bad Request)} if the copyBook has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/copy-books")
    public ResponseEntity<CopyBookDTO> createCopyBook(@RequestBody CopyBookDTO copyBookDTO) throws URISyntaxException {
        log.debug("REST request to save CopyBook : {}", copyBookDTO);
        if (copyBookDTO.getId() != null) {
            throw new BadRequestAlertException("A new copyBook cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CopyBookDTO result = copyBookService.save(copyBookDTO);
        return ResponseEntity.created(new URI("/api/copy-books/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /copy-books} : Updates an existing copyBook.
     *
     * @param copyBookDTO the copyBookDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated copyBookDTO,
     * or with status {@code 400 (Bad Request)} if the copyBookDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the copyBookDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/copy-books")
    public ResponseEntity<CopyBookDTO> updateCopyBook(@RequestBody CopyBookDTO copyBookDTO) throws URISyntaxException {
        log.debug("REST request to update CopyBook : {}", copyBookDTO);
        if (copyBookDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CopyBookDTO result = copyBookService.save(copyBookDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, copyBookDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /copy-books} : get all the copyBooks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of copyBooks in body.
     */
    @GetMapping("/copy-books")
    public List<CopyBookDTO> getAllCopyBooks() {
        log.debug("REST request to get all CopyBooks");
        return copyBookService.findAll();
    }

    /**
     * {@code GET  /copy-books/:id} : get the "id" copyBook.
     *
     * @param id the id of the copyBookDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the copyBookDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/copy-books/{id}")
    public ResponseEntity<CopyBookDTO> getCopyBook(@PathVariable Long id) {
        log.debug("REST request to get CopyBook : {}", id);
        Optional<CopyBookDTO> copyBookDTO = copyBookService.findOne(id);
        return ResponseUtil.wrapOrNotFound(copyBookDTO);
    }

    /**
     * {@code DELETE  /copy-books/:id} : delete the "id" copyBook.
     *
     * @param id the id of the copyBookDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/copy-books/{id}")
    public ResponseEntity<Void> deleteCopyBook(@PathVariable Long id) {
        log.debug("REST request to delete CopyBook : {}", id);
        copyBookService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
