package com.opussoftware.service.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.opussoftware.web.rest.TestUtil;

public class LibraryUserDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LibraryUserDTO.class);
        LibraryUserDTO libraryUserDTO1 = new LibraryUserDTO();
        libraryUserDTO1.setId(1L);
        LibraryUserDTO libraryUserDTO2 = new LibraryUserDTO();
        assertThat(libraryUserDTO1).isNotEqualTo(libraryUserDTO2);
        libraryUserDTO2.setId(libraryUserDTO1.getId());
        assertThat(libraryUserDTO1).isEqualTo(libraryUserDTO2);
        libraryUserDTO2.setId(2L);
        assertThat(libraryUserDTO1).isNotEqualTo(libraryUserDTO2);
        libraryUserDTO1.setId(null);
        assertThat(libraryUserDTO1).isNotEqualTo(libraryUserDTO2);
    }
}
