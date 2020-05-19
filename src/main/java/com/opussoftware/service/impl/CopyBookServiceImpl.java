package com.opussoftware.service.impl;

import com.opussoftware.service.CopyBookService;
import com.opussoftware.domain.CopyBook;
import com.opussoftware.repository.CopyBookRepository;
import com.opussoftware.service.dto.CopyBookDTO;
import com.opussoftware.service.mapper.CopyBookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CopyBook}.
 */
@Service
@Transactional
public class CopyBookServiceImpl implements CopyBookService {

    private final Logger log = LoggerFactory.getLogger(CopyBookServiceImpl.class);

    private final CopyBookRepository copyBookRepository;

    private final CopyBookMapper copyBookMapper;

    public CopyBookServiceImpl(CopyBookRepository copyBookRepository, CopyBookMapper copyBookMapper) {
        this.copyBookRepository = copyBookRepository;
        this.copyBookMapper = copyBookMapper;
    }

    /**
     * Save a copyBook.
     *
     * @param copyBookDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CopyBookDTO save(CopyBookDTO copyBookDTO) {
        log.debug("Request to save CopyBook : {}", copyBookDTO);
        CopyBook copyBook = copyBookMapper.toEntity(copyBookDTO);
        copyBook = copyBookRepository.save(copyBook);
        return copyBookMapper.toDto(copyBook);
    }

    /**
     * Get all the copyBooks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CopyBookDTO> findAll() {
        log.debug("Request to get all CopyBooks");
        return copyBookRepository.findAll().stream()
            .map(copyBookMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one copyBook by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CopyBookDTO> findOne(Long id) {
        log.debug("Request to get CopyBook : {}", id);
        return copyBookRepository.findById(id)
            .map(copyBookMapper::toDto);
    }

    /**
     * Delete the copyBook by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CopyBook : {}", id);
        copyBookRepository.deleteById(id);
    }
}
