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
 * A Author.
 */
@Data
@Entity
@Table(name = "author")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "authors")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Author name(String name) {
        this.name = name;
        return this;
    }

    public Author books(Set<Book> books) {
        this.books = books;
        return this;
    }

    public Author addBooks(Book book) {
        this.books.add(book);
        book.getAuthors().add(this);
        return this;
    }

    public Author removeBooks(Book book) {
        this.books.remove(book);
        book.getAuthors().remove(this);
        return this;
    }
}
