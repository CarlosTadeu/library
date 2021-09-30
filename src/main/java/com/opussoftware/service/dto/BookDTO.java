package com.opussoftware.service.dto;

import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.opussoftware.domain.Book} entity.
 */
@Data
public class BookDTO implements Serializable {

    private Long id;

    private String isbn;

    private String title;

    private String publisher;

    private Integer publicationYear;

    private Long totalCopies;
    private Long availableCopies;
    private Set<SubjectDTO> subjects = new HashSet<>();
    private Set<AuthorDTO> authors = new HashSet<>();
}
