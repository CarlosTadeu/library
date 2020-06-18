package com.opussoftware.repository;

import com.opussoftware.domain.LibraryUser;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the LibraryUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {

    LibraryUser findByRg(String rg);

    Optional<LibraryUser> findByCpf(String cpf);
}
