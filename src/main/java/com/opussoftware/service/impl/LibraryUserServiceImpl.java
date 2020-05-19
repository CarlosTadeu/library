package com.opussoftware.service.impl;

import com.opussoftware.service.LibraryUserService;
import com.opussoftware.domain.LibraryUser;
import com.opussoftware.repository.LibraryUserRepository;
import com.opussoftware.service.dto.LibraryUserDTO;
import com.opussoftware.service.mapper.LibraryUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LibraryUser}.
 */
@Service
@Transactional
public class LibraryUserServiceImpl implements LibraryUserService {

    private final Logger log = LoggerFactory.getLogger(LibraryUserServiceImpl.class);

    private final LibraryUserRepository libraryUserRepository;

    private final LibraryUserMapper libraryUserMapper;

    public LibraryUserServiceImpl(LibraryUserRepository libraryUserRepository, LibraryUserMapper libraryUserMapper) {
        this.libraryUserRepository = libraryUserRepository;
        this.libraryUserMapper = libraryUserMapper;
    }

    /**
     * Save a libraryUser.
     *
     * @param libraryUserDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LibraryUserDTO save(LibraryUserDTO libraryUserDTO) {
        log.debug("Request to save LibraryUser : {}", libraryUserDTO);
        LibraryUser libraryUser = libraryUserMapper.toEntity(libraryUserDTO);
        libraryUser = libraryUserRepository.save(libraryUser);
        return libraryUserMapper.toDto(libraryUser);
    }

    /**
     * Get all the libraryUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LibraryUserDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LibraryUsers");
        return libraryUserRepository.findAll(pageable)
            .map(libraryUserMapper::toDto);
    }

    /**
     * Get one libraryUser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LibraryUserDTO> findOne(Long id) {
        log.debug("Request to get LibraryUser : {}", id);
        return libraryUserRepository.findById(id)
            .map(libraryUserMapper::toDto);
    }

    /**
     * Delete the libraryUser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LibraryUser : {}", id);
        libraryUserRepository.deleteById(id);
    }
}
