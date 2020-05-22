package com.opussoftware.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.opussoftware.domain.CopyBook} entity.
 */
public class CopyBookDTO implements Serializable {

    private Long id;

    private Boolean available;


    private Long bookId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CopyBookDTO copyBookDTO = (CopyBookDTO) o;
        if (copyBookDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), copyBookDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CopyBookDTO{" +
            "id=" + getId() +
            ", available='" + isAvailable() + "'" +
            ", bookId=" + getBookId() +
            "}";
    }
}
