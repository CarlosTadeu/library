package com.opussoftware.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.opussoftware.domain.Author} entity.
 */
@Getter
@Setter
public class AuthorDTO implements Serializable {

    private Long id;

    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthorDTO authorDTO = (AuthorDTO) o;
        if (authorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), authorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
