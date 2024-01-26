package com.enviro.assessment.grade001.moretelemabotja.springboot_application.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collections;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto.InvestorDto;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto.ProductDto;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto.WithdrawalNoticeDto;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Investor;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Product;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.WithdrawalNotice;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.repository.InvestorRepository;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.repository.ProductRepository;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.repository.WithdrawalNoticeRepository;

@Service
public class WithdrawalNoticeService {

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WithdrawalNoticeRepository withdrawalNoticeRepository;

    @Autowired
//    private NotificationService notificationService;

    public InvestorDto getInvestorDetails(Long investorId) {
        Optional<Investor> optionalInvestor = investorRepository.findById(investorId);
        Investor investor = optionalInvestor.orElseThrow(() -> new EntityNotFoundException("Investor not found with ID: " + investorId));
        InvestorDto investorDto = convertToInvestorDto(investor);
        return investorDto;
    }

    public List<ProductDto> getInvestorProducts(Long investorId) {
        List<Product> products = productRepository.findAllById(investorId);
        if (products != null && !products.isEmpty()) {
            List<ProductDto> productDtos = products.stream()
                    .map(this::convertToProductDto)
                    .collect(Collectors.toList());
            return productDtos;
        } else {
            return Collections.emptyList();
        }
    }

    public String createWithdrawalNotice(WithdrawalNoticeDto withdrawalNoticeDto) {
        List<ProductDto> productDtos = getInvestorProducts(withdrawalNoticeDto.getInvestorId());
        // notificationService.sendWithdrawalNotification(
        //         productDto.getName(),
        //         currentBalance,
        //         withdrawalAmount,
        //         currentBalance - withdrawalAmount
        // );
        if (productDtos.isEmpty()) {
            return "No products found for the investor.";
        }
        String selectedProductName = withdrawalNoticeDto.getProductName();
        
        boolean productExists = productDtos.stream()
                .anyMatch(productDto -> selectedProductName.equalsIgnoreCase(productDto.getName()));
    
        if (!productExists) {
            return "Selected product does not exist for the investor.";
        }
    
        ProductDto productDto = productDtos.stream()
                .filter(product -> selectedProductName.equalsIgnoreCase(product.getName()))
                .findFirst()
                .orElse(null);
    
        if ("RETIREMENT".equalsIgnoreCase(productDto.getType())) {
            InvestorDto investorDto = getInvestorDetails(withdrawalNoticeDto.getInvestorId());
            int investorAge = investorDto.getAge();
    
            if (investorAge <= 65) {
                return "Investor must be older than 65 for RETIREMENT withdrawals.";
            }
        }
    
        double withdrawalAmount = withdrawalNoticeDto.getWithdrawalAmount();
        double currentBalance = productDto.getCurrentBalance();
    
        if (withdrawalAmount > currentBalance) {
            return "Withdrawal amount cannot be greater than current balance.";
        }
    
        double maxAllowedWithdrawal = 0.9 * currentBalance;
        if (withdrawalAmount > maxAllowedWithdrawal) {
            return "Withdrawal amount exceeds the maximum allowed (90% of current balance).";
        }
    
        WithdrawalNotice withdrawalNotice = convertToWithdrawalNoticeEntity(withdrawalNoticeDto);
        Investor investor = investorRepository.findById(withdrawalNoticeDto.getInvestorId())
                .orElseThrow(() -> new EntityNotFoundException("Investor not found with ID: " + withdrawalNoticeDto.getInvestorId()));
        withdrawalNotice.setInvestor(investor);
        withdrawalNoticeRepository.save(withdrawalNotice);
    
        return "Withdrawal Notice successfully created!";
    }
    

    private InvestorDto convertToInvestorDto(Investor investor) {
        if (investor == null) {
            return null;
        }
        InvestorDto investorDto = new InvestorDto();
        investorDto.setInvestorId(investor.getInvestorId());
        investorDto.setName(investor.getName());
        investorDto.setSurname(investor.getSurname());
        investorDto.setAge(investor.getAge());
        investorDto.setAddress(investor.getAddress());
        investorDto.setDateOfBirth(investor.getDateOfBirth());
        investorDto.setContact(investor.getContact());
        return investorDto;
    }

    private ProductDto convertToProductDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto productDto = new ProductDto();
        productDto.setType(product.getType());
        productDto.setName(product.getName());
        productDto.setCurrentBalance(product.getCurrentBalance());
        return productDto;
    }

    private WithdrawalNotice convertToWithdrawalNoticeEntity(WithdrawalNoticeDto withdrawalNoticeDto) {
        WithdrawalNotice withdrawalNotice = new WithdrawalNotice();
        withdrawalNotice.setWithdrawalName(withdrawalNoticeDto.getProductName());
        withdrawalNotice.setWithdrawalAmount(withdrawalNoticeDto.getWithdrawalAmount());
        withdrawalNotice.setPreviousBalance(withdrawalNoticeDto.getPreviousBalance());
        withdrawalNotice.setWithdrawalDate(withdrawalNoticeDto.getWithdrawalDate());
        withdrawalNotice.setBankName(withdrawalNoticeDto.getBankName());
        withdrawalNotice.setAccountHolderName(withdrawalNoticeDto.getAccountHolderName());
        withdrawalNotice.setAccountNumber(withdrawalNoticeDto.getAccountNumber());
        withdrawalNotice.setBranchCode(withdrawalNoticeDto.getBranchCode());
        return withdrawalNotice;
    }
}
