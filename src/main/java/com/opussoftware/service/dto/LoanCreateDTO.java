package com.opussoftware.service.dto;

import lombok.Data;

/**
 * A DTO for create the {@link com.opussoftware.domain.Loan} entity.
 */
@Data
public class LoanCreateDTO {

    private String userCpf;
    private Long copyBookId;
}
