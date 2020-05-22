package com.opussoftware.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.opussoftware.domain.StudentType} entity.
 */
public class StudentTypeDTO implements Serializable {

    private Long id;

    private String studentType;

    private Integer numberOfDaysLoan;

    private Integer numberOfDaysRenewal;

    private Integer maxBooksOnLoan;

    private Integer maxRenewalNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public Integer getNumberOfDaysLoan() {
        return numberOfDaysLoan;
    }

    public void setNumberOfDaysLoan(Integer numberOfDaysLoan) {
        this.numberOfDaysLoan = numberOfDaysLoan;
    }

    public Integer getNumberOfDaysRenewal() {
        return numberOfDaysRenewal;
    }

    public void setNumberOfDaysRenewal(Integer numberOfDaysRenewal) {
        this.numberOfDaysRenewal = numberOfDaysRenewal;
    }

    public Integer getMaxBooksOnLoan() {
        return maxBooksOnLoan;
    }

    public void setMaxBooksOnLoan(Integer maxBooksOnLoan) {
        this.maxBooksOnLoan = maxBooksOnLoan;
    }

    public Integer getMaxRenewalNumber() {
        return maxRenewalNumber;
    }

    public void setMaxRenewalNumber(Integer maxRenewalNumber) {
        this.maxRenewalNumber = maxRenewalNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StudentTypeDTO studentTypeDTO = (StudentTypeDTO) o;
        if (studentTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), studentTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StudentTypeDTO{" +
            "id=" + getId() +
            ", studentType='" + getStudentType() + "'" +
            ", numberOfDaysLoan=" + getNumberOfDaysLoan() +
            ", numberOfDaysRenewal=" + getNumberOfDaysRenewal() +
            ", maxBooksOnLoan=" + getMaxBooksOnLoan() +
            ", maxRenewalNumber=" + getMaxRenewalNumber() +
            "}";
    }
}
