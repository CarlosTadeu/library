package com.opussoftware.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A StudentType.
 */
@Entity
@Table(name = "student_types")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StudentType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_type")
    private String studentType;

    @Column(name = "number_of_days_loan")
    private Integer numberOfDaysLoan;

    @Column(name = "number_of_days_renewal")
    private Integer numberOfDaysRenewal;

    @Column(name = "max_books_on_loan")
    private Integer maxBooksOnLoan;

    @Column(name = "max_renewal_number")
    private Integer maxRenewalNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentType() {
        return studentType;
    }

    public StudentType studentType(String studentType) {
        this.studentType = studentType;
        return this;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public Integer getNumberOfDaysLoan() {
        return numberOfDaysLoan;
    }

    public StudentType numberOfDaysLoan(Integer numberOfDaysLoan) {
        this.numberOfDaysLoan = numberOfDaysLoan;
        return this;
    }

    public void setNumberOfDaysLoan(Integer numberOfDaysLoan) {
        this.numberOfDaysLoan = numberOfDaysLoan;
    }

    public Integer getNumberOfDaysRenewal() {
        return numberOfDaysRenewal;
    }

    public StudentType numberOfDaysRenewal(Integer numberOfDaysRenewal) {
        this.numberOfDaysRenewal = numberOfDaysRenewal;
        return this;
    }

    public void setNumberOfDaysRenewal(Integer numberOfDaysRenewal) {
        this.numberOfDaysRenewal = numberOfDaysRenewal;
    }

    public Integer getMaxBooksOnLoan() {
        return maxBooksOnLoan;
    }

    public StudentType maxBooksOnLoan(Integer maxBooksOnLoan) {
        this.maxBooksOnLoan = maxBooksOnLoan;
        return this;
    }

    public void setMaxBooksOnLoan(Integer maxBooksOnLoan) {
        this.maxBooksOnLoan = maxBooksOnLoan;
    }

    public Integer getMaxRenewalNumber() {
        return maxRenewalNumber;
    }

    public StudentType maxRenewalNumber(Integer maxRenewalNumber) {
        this.maxRenewalNumber = maxRenewalNumber;
        return this;
    }

    public void setMaxRenewalNumber(Integer maxRenewalNumber) {
        this.maxRenewalNumber = maxRenewalNumber;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentType)) {
            return false;
        }
        return id != null && id.equals(((StudentType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "StudentType{" +
            "id=" + getId() +
            ", studentType='" + getStudentType() + "'" +
            ", numberOfDaysLoan=" + getNumberOfDaysLoan() +
            ", numberOfDaysRenewal=" + getNumberOfDaysRenewal() +
            ", maxBooksOnLoan=" + getMaxBooksOnLoan() +
            ", maxRenewalNumber=" + getMaxRenewalNumber() +
            "}";
    }
}
