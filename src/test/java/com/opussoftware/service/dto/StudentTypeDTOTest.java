package com.opussoftware.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.opussoftware.web.rest.TestUtil;

public class StudentTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(StudentTypeDTO.class);
        StudentTypeDTO studentTypeDTO1 = new StudentTypeDTO();
        studentTypeDTO1.setId(1L);
        StudentTypeDTO studentTypeDTO2 = new StudentTypeDTO();
        assertThat(studentTypeDTO1).isNotEqualTo(studentTypeDTO2);
        studentTypeDTO2.setId(studentTypeDTO1.getId());
        assertThat(studentTypeDTO1).isEqualTo(studentTypeDTO2);
        studentTypeDTO2.setId(2L);
        assertThat(studentTypeDTO1).isNotEqualTo(studentTypeDTO2);
        studentTypeDTO1.setId(null);
        assertThat(studentTypeDTO1).isNotEqualTo(studentTypeDTO2);
    }
}
