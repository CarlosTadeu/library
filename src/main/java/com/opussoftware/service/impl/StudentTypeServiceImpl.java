package com.opussoftware.service.impl;

import com.opussoftware.service.StudentTypeService;
import com.opussoftware.domain.StudentType;
import com.opussoftware.repository.StudentTypeRepository;
import com.opussoftware.service.dto.StudentTypeDTO;
import com.opussoftware.service.mapper.StudentTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link StudentType}.
 */
@Service
@Transactional
public class StudentTypeServiceImpl implements StudentTypeService {

    private final Logger log = LoggerFactory.getLogger(StudentTypeServiceImpl.class);

    private final StudentTypeRepository studentTypeRepository;

    private final StudentTypeMapper studentTypeMapper;

    public StudentTypeServiceImpl(StudentTypeRepository studentTypeRepository, StudentTypeMapper studentTypeMapper) {
        this.studentTypeRepository = studentTypeRepository;
        this.studentTypeMapper = studentTypeMapper;
    }

    /**
     * Save a studentType.
     *
     * @param studentTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public StudentTypeDTO save(StudentTypeDTO studentTypeDTO) {
        log.debug("Request to save StudentType : {}", studentTypeDTO);
        StudentType studentType = studentTypeMapper.toEntity(studentTypeDTO);
        studentType = studentTypeRepository.save(studentType);
        return studentTypeMapper.toDto(studentType);
    }

    /**
     * Get all the studentTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentTypeDTO> findAll() {
        log.debug("Request to get all StudentTypes");
        return studentTypeRepository.findAll().stream()
            .map(studentTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one studentType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StudentTypeDTO> findOne(Long id) {
        log.debug("Request to get StudentType : {}", id);
        return studentTypeRepository.findById(id)
            .map(studentTypeMapper::toDto);
    }

    /**
     * Delete the studentType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StudentType : {}", id);
        studentTypeRepository.deleteById(id);
    }
}
