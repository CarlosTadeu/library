package com.opussoftware.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.opussoftware.domain.Loan} entity.
 */
public class LoanDTO implements Serializable {
    
    private Long id;

    private LocalDate loanDate;

    private LocalDate dateReturned;

    private LocalDate dateToBeReturned;

    private Integer numberOfRenewals;


    private Long userId;

    private Long copyBookId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public LocalDate getDateToBeReturned() {
        return dateToBeReturned;
    }

    public void setDateToBeReturned(LocalDate dateToBeReturned) {
        this.dateToBeReturned = dateToBeReturned;
    }

    public Integer getNumberOfRenewals() {
        return numberOfRenewals;
    }

    public void setNumberOfRenewals(Integer numberOfRenewals) {
        this.numberOfRenewals = numberOfRenewals;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long libraryUserId) {
        this.userId = libraryUserId;
    }

    public Long getCopyBookId() {
        return copyBookId;
    }

    public void setCopyBookId(Long copyBookId) {
        this.copyBookId = copyBookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoanDTO loanDTO = (LoanDTO) o;
        if (loanDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loanDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
            "id=" + getId() +
            ", loanDate='" + getLoanDate() + "'" +
            ", dateReturned='" + getDateReturned() + "'" +
            ", dateToBeReturned='" + getDateToBeReturned() + "'" +
            ", numberOfRenewals=" + getNumberOfRenewals() +
            ", userId=" + getUserId() +
            ", copyBookId=" + getCopyBookId() +
            "}";
    }
}
