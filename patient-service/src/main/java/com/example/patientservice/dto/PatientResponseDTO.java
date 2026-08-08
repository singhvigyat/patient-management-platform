package com.example.patientservice.dto;

// return only what's needed, in model we also have registerDate, but we are not returning
// that back, because that doesn't make any sense, to be in DTO, that's only there
// for auditing purposes, & to be stored in the database.
public class PatientResponseDTO {
    private String id;
    private String name;
    private String email ;
    private String address;
    private String dateOfBirth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
