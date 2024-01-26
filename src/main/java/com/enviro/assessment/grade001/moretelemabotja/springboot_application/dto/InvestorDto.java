package com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Product;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Investor;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.WithdrawalNotice;


@Entity
public class InvestorDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long investorId;

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private Integer age;
    private String address;
    private String contact;
    private List<ProductDto> products;

    public InvestorDto() {
    }

    public InvestorDto(Long investorId, String name, String surname, LocalDate dateOfBirth, Integer age,
                       String address, String contact, List<ProductDto> products) {
        this.investorId = investorId;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.products = products;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getAddress() {
        return this.address;
    }

    public String getContact() {
        return this.contact;
    }

    public List<ProductDto> getProducts() {
        return this.products;
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

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}