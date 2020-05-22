package com.opussoftware.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CopyBook.
 */
@Data
@Entity
@Table(name = "copy_book")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CopyBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "available")
    private Boolean available;

    @ManyToOne
    @JsonIgnoreProperties("copyBook")
    private Book book;

    public Boolean isAvailable() {
        return available;
    }

    public CopyBook available(Boolean available) {
        this.available = available;
        return this;
    }

    public CopyBook book(Book book) {
        this.book = book;
        return this;
    }
}
