package com.opussoftware.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A LibraryUser.
 */
@Data
@Entity
@Table(name = "library_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LibraryUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "rg")
    private String rg;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "suspension_date")
    private LocalDate suspensionDate;

    @ManyToOne
    @JsonIgnoreProperties("libraryUsers")
    private StudentType studentType;

    public LibraryUser cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public LibraryUser rg(String rg) {
        this.rg = rg;
        return this;
    }

    public LibraryUser name(String name) {
        this.name = name;
        return this;
    }

    public LibraryUser username(String username) {
        return this;
    }

    public LibraryUser address(String address) {
        this.address = address;
        return this;
    }

    public LibraryUser email(String email) {
        this.email = email;
        return this;
    }

    public LibraryUser phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public LibraryUser suspensionDate(LocalDate suspensionDate) {
        this.suspensionDate = suspensionDate;
        return this;
    }

    public LibraryUser studentType(StudentType studentType) {
        this.studentType = studentType;
        return this;
    }
}
