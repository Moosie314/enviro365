package com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto;

import java.util.List;
import javax.persistence.*;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Investor;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Product;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.WithdrawalNotice;

@Entity
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String type;
    private String name;
    private double currentBalance;

    public ProductDto() {
    }

    public ProductDto(Long productId, String type, String name, double currentBalance) {
        this.productId = productId;
        this.type = type;
        this.name = name;
        this.currentBalance = currentBalance;

    }

    public Long getProductId() {
        return this.productId;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public double getCurrentBalance() {
        return this.currentBalance;
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
}