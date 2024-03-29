package com.opussoftware.repository;

import com.opussoftware.domain.Subject;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Subject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject findBySubjectIsLike(String subject);
}
