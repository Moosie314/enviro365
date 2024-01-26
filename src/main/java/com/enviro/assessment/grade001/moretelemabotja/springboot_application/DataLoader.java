package com.enviro.assessment.grade001.moretelemabotja.springboot_application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Investor;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Product;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.repository.InvestorRepository;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.repository.ProductRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final InvestorRepository investorRepository;
    private final ProductRepository productRepository;

    public DataLoader(InvestorRepository investorRepository, ProductRepository productRepository) {
        this.investorRepository = investorRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Investor investor1 = new Investor();
        investor1.setName("John");
        investor1.setSurname("Doe");
        investor1.setDateOfBirth(LocalDate.parse("1990-01-01"));
        investor1.setAge(32);
        investor1.setAddress("123 Main St");
        investor1.setContact("john.doe@example.com");
        investor1.setInvestorId(105225061086L);

        Product product1 = new Product();
        product1.setType("Savings");
        product1.setName("Emergency Fund");
        product1.setCurrentBalance(5000.0);
        product1.setInvestor(investor1);

        Product product2 = new Product();
        product2.setType("Retirement");
        product2.setName("401(k) Plan");
        product2.setCurrentBalance(10000.0);
        product2.setInvestor(investor1);

        List<Product> products1 = new ArrayList<>();
        products1.add(product1);
        products1.add(product2);

        investor1.setProducts(products1);
        investorRepository.save(investor1);

        Investor investor2 = new Investor();
        investor2.setInvestorId(970422098081L);
        investor2.setName("Jane");
        investor2.setSurname("Smith");
        investor2.setDateOfBirth(LocalDate.parse("1985-05-15"));
        investor2.setAge(37);
        investor2.setAddress("456 Oak St");
        investor2.setContact("jane.smith@example.com");

        Product product3 = new Product();
        product3.setType("Savings");
        product3.setName("Vacation Fund");
        product3.setCurrentBalance(8000.0);
        product3.setInvestor(investor2);

        Product product4 = new Product();
        product4.setType("Retirement");
        product4.setName("IRA Account");
        product4.setCurrentBalance(15000.0);
        product4.setInvestor(investor2);

        List<Product> products2 = new ArrayList<>();
        products2.add(product3);
        products2.add(product4);

        investor2.setProducts(products2);
        investorRepository.save(investor2);
    }
}