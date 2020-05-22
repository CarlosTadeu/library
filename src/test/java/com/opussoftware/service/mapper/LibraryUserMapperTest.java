package com.opussoftware.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LibraryUserMapperTest {

    private LibraryUserMapper libraryUserMapper;

    @BeforeEach
    public void setUp() {
        libraryUserMapper = new LibraryUserMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(libraryUserMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(libraryUserMapper.fromId(null)).isNull();
    }
}
