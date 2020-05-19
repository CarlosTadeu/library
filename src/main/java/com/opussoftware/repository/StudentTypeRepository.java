package com.opussoftware.repository;

import com.opussoftware.domain.StudentType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the StudentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentTypeRepository extends JpaRepository<StudentType, Long> {
}
