package com.enviro.assessment.grade001.moretelemabotja.springboot_application.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "current_balance")
    private double currentBalance;



    public Product() {
    }

    public Product(String type, String name, double currentBalance, Investor investor) {
        this.type = type;
        this.name = name;
        this.currentBalance = currentBalance;
        this.investor = investor;
    }


    public Long getProductId() {
        return productId;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public Investor getInvestor(){
        return this.investor;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
    
    public void setInvestor(Investor investor){
        this.investor = investor;
    }
}
