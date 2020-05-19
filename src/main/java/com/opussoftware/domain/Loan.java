package com.opussoftware.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Loan.
 */
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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public Loan loanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
        return this;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public Loan dateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
        return this;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    public LocalDate getDateToBeReturned() {
        return dateToBeReturned;
    }

    public Loan dateToBeReturned(LocalDate dateToBeReturned) {
        this.dateToBeReturned = dateToBeReturned;
        return this;
    }

    public void setDateToBeReturned(LocalDate dateToBeReturned) {
        this.dateToBeReturned = dateToBeReturned;
    }

    public Integer getNumberOfRenewals() {
        return numberOfRenewals;
    }

    public Loan numberOfRenewals(Integer numberOfRenewals) {
        this.numberOfRenewals = numberOfRenewals;
        return this;
    }

    public void setNumberOfRenewals(Integer numberOfRenewals) {
        this.numberOfRenewals = numberOfRenewals;
    }

    public LibraryUser getUser() {
        return user;
    }

    public Loan user(LibraryUser libraryUser) {
        this.user = libraryUser;
        return this;
    }

    public void setUser(LibraryUser libraryUser) {
        this.user = libraryUser;
    }

    public CopyBook getCopyBook() {
        return copyBook;
    }

    public Loan copyBook(CopyBook copyBook) {
        this.copyBook = copyBook;
        return this;
    }

    public void setCopyBook(CopyBook copyBook) {
        this.copyBook = copyBook;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Loan)) {
            return false;
        }
        return id != null && id.equals(((Loan) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Loan{" +
            "id=" + getId() +
            ", loanDate='" + getLoanDate() + "'" +
            ", dateReturned='" + getDateReturned() + "'" +
            ", dateToBeReturned='" + getDateToBeReturned() + "'" +
            ", numberOfRenewals=" + getNumberOfRenewals() +
            "}";
    }
}
