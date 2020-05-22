package com.opussoftware.service.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.opussoftware.web.rest.TestUtil;

public class CopyBookDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CopyBookDTO.class);
        CopyBookDTO copyBookDTO1 = new CopyBookDTO();
        copyBookDTO1.setId(1L);
        CopyBookDTO copyBookDTO2 = new CopyBookDTO();
        assertThat(copyBookDTO1).isNotEqualTo(copyBookDTO2);
        copyBookDTO2.setId(copyBookDTO1.getId());
        assertThat(copyBookDTO1).isEqualTo(copyBookDTO2);
        copyBookDTO2.setId(2L);
        assertThat(copyBookDTO1).isNotEqualTo(copyBookDTO2);
        copyBookDTO1.setId(null);
        assertThat(copyBookDTO1).isNotEqualTo(copyBookDTO2);
    }
}
