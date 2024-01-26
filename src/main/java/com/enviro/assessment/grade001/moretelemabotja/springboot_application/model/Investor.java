package com.enviro.assessment.grade001.moretelemabotja.springboot_application.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "investors")
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technical_id")
    private Long technicalId;
    @Column(name = "investor_id")
    private Long investorId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "age")
    private Integer age;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contact;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL)
    private List<Product> products;

    public Investor() {
    }

    public Investor(Long investorId, String name, String surname, LocalDate dateOfBirth, Integer age,
                    String address, String contact, List<Product> products) {
        this.investorId = investorId;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.products = products;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
