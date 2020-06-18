package com.opussoftware.repository;

import com.opussoftware.domain.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Book entity.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select distinct book from Book book left join fetch book.subjects left join fetch book.authors",
        countQuery = "select count(distinct book) from Book book")
    Page<Book> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct book from Book book left join fetch book.subjects left join fetch book.authors")
    List<Book> findAllWithEagerRelationships();

    @Query("select book from Book book left join fetch book.subjects left join fetch book.authors where book.id =:id")
    Optional<Book> findOneWithEagerRelationships(@Param("id") Long id);

//    @Query("select distinct book from Book book left join fetch book.subjects left join fetch book.authors where book.title like %:title%")
    List<Book> findAllByTitleContaining(String title);

    List<Book> findAllByAuthorsContaining(String author);

    List<Book> findAllBySubjectsIsLike(String subject);

    List<Book> findAllByPublisherContaining(String publisher);

    List<Book> findAllByIsbnStartingWith(String isbn);

    Boolean existsBooksByIsbn(String isbn);
}
