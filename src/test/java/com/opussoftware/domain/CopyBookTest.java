package com.opussoftware.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.opussoftware.web.rest.TestUtil;

public class CopyBookTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CopyBook.class);
        CopyBook copyBook1 = new CopyBook();
        copyBook1.setId(1L);
        CopyBook copyBook2 = new CopyBook();
        copyBook2.setId(copyBook1.getId());
        assertThat(copyBook1).isEqualTo(copyBook2);
        copyBook2.setId(2L);
        assertThat(copyBook1).isNotEqualTo(copyBook2);
        copyBook1.setId(null);
        assertThat(copyBook1).isNotEqualTo(copyBook2);
    }
}
