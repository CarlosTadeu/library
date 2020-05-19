package com.opussoftware.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.opussoftware.domain.LibraryUser} entity.
 */
public class LibraryUserDTO implements Serializable {
    
    private Long id;

    private String cpf;

    private String rg;

    private String name;

    private String username;

    private String address;

    private String email;

    private String phoneNumber;

    private LocalDate suspensionDate;


    private Long studentTypeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getSuspensionDate() {
        return suspensionDate;
    }

    public void setSuspensionDate(LocalDate suspensionDate) {
        this.suspensionDate = suspensionDate;
    }

    public Long getStudentTypeId() {
        return studentTypeId;
    }

    public void setStudentTypeId(Long studentTypeId) {
        this.studentTypeId = studentTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LibraryUserDTO libraryUserDTO = (LibraryUserDTO) o;
        if (libraryUserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), libraryUserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LibraryUserDTO{" +
            "id=" + getId() +
            ", cpf='" + getCpf() + "'" +
            ", rg='" + getRg() + "'" +
            ", name='" + getName() + "'" +
            ", username='" + getUsername() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", suspensionDate='" + getSuspensionDate() + "'" +
            ", studentTypeId=" + getStudentTypeId() +
            "}";
    }
}
