package com.opussoftware.repository;

import com.opussoftware.domain.LibraryUser;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LibraryUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {
}
