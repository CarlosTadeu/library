package com.opussoftware.repository;

import com.opussoftware.domain.CopyBook;
import com.opussoftware.domain.LibraryUser;
import com.opussoftware.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Loan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findByUserAndDateReturnedIsNull(LibraryUser libraryUser);

    List<Loan> findAllByUser(LibraryUser libraryUser);

    Optional<Loan> findByCopyBookAndDateReturnedIsNull(CopyBook copyBook);
}
