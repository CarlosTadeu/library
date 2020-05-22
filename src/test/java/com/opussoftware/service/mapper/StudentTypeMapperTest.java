package com.opussoftware.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTypeMapperTest {

    private StudentTypeMapper studentTypeMapper;

    @BeforeEach
    public void setUp() {
        studentTypeMapper = new StudentTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(studentTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(studentTypeMapper.fromId(null)).isNull();
    }
}
