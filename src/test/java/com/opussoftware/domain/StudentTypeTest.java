package com.opussoftware.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.opussoftware.web.rest.TestUtil;

public class StudentTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentType.class);
        StudentType studentType1 = new StudentType();
        studentType1.setId(1L);
        StudentType studentType2 = new StudentType();
        studentType2.setId(studentType1.getId());
        assertThat(studentType1).isEqualTo(studentType2);
        studentType2.setId(2L);
        assertThat(studentType1).isNotEqualTo(studentType2);
        studentType1.setId(null);
        assertThat(studentType1).isNotEqualTo(studentType2);
    }
}
