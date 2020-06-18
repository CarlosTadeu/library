package com.opussoftware.repository;

import com.opussoftware.domain.Book;
import com.opussoftware.domain.CopyBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Spring Data  repository for the CopyBook entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CopyBookRepository extends JpaRepository<CopyBook, Long> {

    @Query("select count(distinct copyBook) from CopyBook copyBook where copyBook.book =:book")
    Long findTotalCopiesByBook(@Param("book") Book book);

    @Query("select count(distinct copyBook) from CopyBook copyBook where copyBook.available = true and copyBook.book =:book")
    Long findAvailableCopiesByBook(@Param("book") Book book);
}
