package com.opussoftware.service.dto;

import lombok.*;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.opussoftware.domain.Loan} entity.
 */
@Data
public class LoanDTO implements Serializable {

    private Long id;

    private LocalDate loanDate;

    private LocalDate dateReturned;

    private LocalDate dateToBeReturned;

    private Integer numberOfRenewals;


    private Long userId;

    private Long copyBookId;
}
