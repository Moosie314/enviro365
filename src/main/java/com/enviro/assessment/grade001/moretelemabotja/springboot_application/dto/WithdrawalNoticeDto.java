package com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Investor;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Product;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.WithdrawalNotice;


@Entity
public class WithdrawalNoticeDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long withdrawalNoticeId;

    private Long investorId;

    private String productName;

    private double withdrawalAmount;

    private double previousBalance;

    private LocalDate withdrawalDate;
    private String productType;
    private String bankName;
    private String accountHolderName;
    private String accountNumber;
    private String branchCode;

    public WithdrawalNoticeDto() {
    }

    public WithdrawalNoticeDto(Long withdrawalNoticeId, double withdrawalAmount, LocalDate withdrawalDate, double previousBalance, Long investorId, 
    String bankName, String accountNumber, String branchCode, String accountHolderNameLong, String productName, String productType) {
        this.withdrawalNoticeId = withdrawalNoticeId;
        this.withdrawalAmount = withdrawalAmount;
        this.withdrawalDate = withdrawalDate;
        this.previousBalance = previousBalance;
        this.investorId = investorId;
        this.productName = productName;
        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.branchCode = branchCode;
        this.productType = productType;
    }
    public Long getWithdrawalNoticeId() {
        return this.withdrawalNoticeId;
    }

    public double getWithdrawalAmount() {
        return this.withdrawalAmount;
    }

    public LocalDate getWithdrawalDate() {
        return this.withdrawalDate;
    }

    public double getPreviousBalance(){
        return this.previousBalance;
    }

    public String getProductName(){
        return this.productName;
    }
    public Long getInvestorId(){
        return this.investorId;
    }
    public String getProductType(){
        return this.productType;  
    }

    public void setProductType(String productType){
        this.productType = productType;
    }
    public void setWithdrawalNoticeId(Long withdrawalNoticeId) {
        this.withdrawalNoticeId = withdrawalNoticeId;
    }

    public void setWithdrawalAmount(double withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public void setWithdrawalDate(LocalDate withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public void setPreviousBalance(double previousBalance){
        this.previousBalance = previousBalance;
    }

    public void setInvestorId(Long investorId){
        this.investorId = investorId;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
}