package com.opussoftware.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Subject.
 */
@Data
@Entity
@Table(name = "subject")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @ManyToMany(mappedBy = "subjects")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Subject subject(String subject) {
        this.subject = subject;
        return this;
    }

    public Subject books(Set<Book> books) {
        this.books = books;
        return this;
    }

    public Subject addBooks(Book book) {
        this.books.add(book);
        book.getSubjects().add(this);
        return this;
    }

    public Subject removeBooks(Book book) {
        this.books.remove(book);
        book.getSubjects().remove(this);
        return this;
    }
}
