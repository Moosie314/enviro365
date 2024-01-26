package com.enviro.assessment.grade001.moretelemabotja.springboot_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto.InvestorDto;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto.ProductDto;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto.WithdrawalNoticeDto;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.service.WithdrawalNoticeService;

@RestController
@RequestMapping("/api")
public class WithdrawalNoticeController {

    @Autowired
    private WithdrawalNoticeService withdrawalNoticeService;

    @GetMapping("/investor/{investorId}")
    public ResponseEntity<InvestorDto> getInvestorDetails(@PathVariable Long investorId) {
        InvestorDto investorDto = withdrawalNoticeService.getInvestorDetails(investorId);
        return new ResponseEntity<>(investorDto, HttpStatus.OK);
    }

    @GetMapping("/products/{investorId}")
    public ResponseEntity<List<ProductDto>> getInvestorProducts(@PathVariable Long investorId) {
        List<ProductDto> products = withdrawalNoticeService.getInvestorProducts(investorId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<String> createWithdrawalNotice(@RequestBody WithdrawalNoticeDto withdrawalNoticeDto) {
        if ("RETIREMENT".equalsIgnoreCase(withdrawalNoticeDto.getProductType())) {
            InvestorDto investorDto = withdrawalNoticeService.getInvestorDetails(withdrawalNoticeDto.getInvestorId());
            int investorAge = investorDto.getAge();
            if (investorAge <= 65) {
                return new ResponseEntity<>("Investor must be older than 65 for RETIREMENT withdrawals.", HttpStatus.BAD_REQUEST);
            }
        }

        double withdrawalAmount = withdrawalNoticeDto.getWithdrawalAmount();
        double currentBalance = withdrawalNoticeDto.getPreviousBalance();
        if (withdrawalAmount > currentBalance) {
            return new ResponseEntity<>("Withdrawal amount cannot be greater than current balance.", HttpStatus.BAD_REQUEST);
        }

        double maxAllowedWithdrawal = 0.9 * currentBalance;
        if (withdrawalAmount > maxAllowedWithdrawal) {
            return new ResponseEntity<>("Withdrawal amount exceeds the maximum allowed (90% of current balance).", HttpStatus.BAD_REQUEST);
        }

        String result = withdrawalNoticeService.createWithdrawalNotice(withdrawalNoticeDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
