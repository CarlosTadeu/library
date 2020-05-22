package com.opussoftware.domain;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Loan.
 */
@Data
@Entity
@Table(name = "loans")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    @Column(name = "date_returned")
    private LocalDate dateReturned;

    @Column(name = "date_to_be_returned")
    private LocalDate dateToBeReturned;

    @Column(name = "number_of_renewals")
    private Integer numberOfRenewals;

    @OneToOne
    @JoinColumn(unique = true)
    private LibraryUser user;

    @OneToOne
    @JoinColumn(unique = true)
    private CopyBook copyBook;

    public Loan loanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
        return this;
    }

    public Loan dateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
        return this;
    }

    public Loan dateToBeReturned(LocalDate dateToBeReturned) {
        this.dateToBeReturned = dateToBeReturned;
        return this;
    }

    public Loan numberOfRenewals(Integer numberOfRenewals) {
        this.numberOfRenewals = numberOfRenewals;
        return this;
    }

    public Loan user(LibraryUser libraryUser) {
        this.user = libraryUser;
        return this;
    }

    public Loan copyBook(CopyBook copyBook) {
        this.copyBook = copyBook;
        return this;
    }
}
