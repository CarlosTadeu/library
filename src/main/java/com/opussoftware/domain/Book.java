package com.opussoftware.domain;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Book.
 */
@Data
@Entity
@Table(name = "book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CopyBook> copyBooks = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "book_subjects",
        joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "subjects_id", referencedColumnName = "id"))
    private Set<Subject> subjects = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "authors_id", referencedColumnName = "id"))
    private Set<Author> authors = new HashSet<>();

    public Book isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public Book title(String title) {
        this.title = title;
        return this;
    }

    public Book publisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Book publicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
        return this;
    }

    public Book copyBooks(Set<CopyBook> copyBooks) {
        this.copyBooks = copyBooks;
        return this;
    }

    public Book addCopyBooks(CopyBook copyBook) {
        this.copyBooks.add(copyBook);
        copyBook.setBook(this);
        return this;
    }

    public Book removeCopyBooks(CopyBook copyBook) {
        this.copyBooks.remove(copyBook);
        copyBook.setBook(null);
        return this;
    }

    public Book subjects(Set<Subject> subjects) {
        this.subjects = subjects;
        return this;
    }

    public Book addSubjects(Subject subject) {
        this.subjects.add(subject);
        subject.getBooks().add(this);
        return this;
    }

    public Book removeSubjects(Subject subject) {
        this.subjects.remove(subject);
        subject.getBooks().remove(this);
        return this;
    }

    public Book authors(Set<Author> authors) {
        this.authors = authors;
        return this;
    }

    public Book addAuthors(Author author) {
        this.authors.add(author);
        author.getBooks().add(this);
        return this;
    }

    public Book removeAuthors(Author author) {
        this.authors.remove(author);
        author.getBooks().remove(this);
        return this;
    }
}
