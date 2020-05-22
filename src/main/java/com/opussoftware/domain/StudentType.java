package com.opussoftware.domain;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A StudentType.
 */
@Data
@Entity
@Table(name = "student_type")
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

    public StudentType studentType(String studentType) {
        this.studentType = studentType;
        return this;
    }

    public StudentType numberOfDaysLoan(Integer numberOfDaysLoan) {
        this.numberOfDaysLoan = numberOfDaysLoan;
        return this;
    }

    public StudentType numberOfDaysRenewal(Integer numberOfDaysRenewal) {
        this.numberOfDaysRenewal = numberOfDaysRenewal;
        return this;
    }

    public StudentType maxBooksOnLoan(Integer maxBooksOnLoan) {
        this.maxBooksOnLoan = maxBooksOnLoan;
        return this;
    }

    public StudentType maxRenewalNumber(Integer maxRenewalNumber) {
        this.maxRenewalNumber = maxRenewalNumber;
        return this;
    }
}
