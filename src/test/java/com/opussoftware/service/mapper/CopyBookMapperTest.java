package com.opussoftware.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CopyBookMapperTest {

    private CopyBookMapper copyBookMapper;

    @BeforeEach
    public void setUp() {
        copyBookMapper = new CopyBookMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(copyBookMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(copyBookMapper.fromId(null)).isNull();
    }
}
