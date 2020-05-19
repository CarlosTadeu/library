package com.opussoftware.repository;

import com.opussoftware.domain.CopyBook;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CopyBook entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CopyBookRepository extends JpaRepository<CopyBook, Long> {
}
