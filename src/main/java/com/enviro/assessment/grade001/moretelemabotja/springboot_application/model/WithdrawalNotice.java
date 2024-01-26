package com.enviro.assessment.grade001.moretelemabotja.springboot_application.model;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "withdrawal_notices")
public class WithdrawalNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "withdrawal_notice_id")
    private Long withdrawalNoticeId;

    @Column(name = "withdrawal_amount")
    private double withdrawalAmount;

    @Column(name = "withdrawal_date")
    private LocalDate withdrawalDate;

    @Column(name = "banking_details")
    private String bankingDetails;

    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;

    @Column(name = "withdrawal_name")
    private String withdrawalName;

    @Column(name = "previous_balance")
    private double previousBalance;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "branch_code")
    private String branchCode;

    public WithdrawalNotice() {
    }

    public WithdrawalNotice(double withdrawalAmount, LocalDate withdrawalDate, String bankingDetails, Investor investor, String withdrawalName, double previousBalance, 
    String bankName, String accountNumber, String branchCode, String accountHolderName) {
        this.withdrawalAmount = withdrawalAmount;
        this.withdrawalDate = withdrawalDate;
        this.bankingDetails = bankingDetails;
        this.investor = investor;
        this.withdrawalName = withdrawalName;
        this.previousBalance = previousBalance;
        this.bankName = bankName;
        this.accountHolderName = accountHolderName;
        this.accountNumber = accountNumber;
        this.branchCode = branchCode;
    }

    public Long getWithdrawalNoticeId() {
        return withdrawalNoticeId;
    }

    public double getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public LocalDate getWithdrawalDate() {
        return withdrawalDate;
    }

    public Investor getInvestor() {
        return investor;
    }

    public double getPreviousBalance(){
        return this.previousBalance;
    }

    public String getWithdrawalName() {
        return withdrawalName;
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

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }


    public void setWithdrawalName(String withdrawalName) {
        this.withdrawalName = withdrawalName;
    }

    public void setPreviousBalance(double previousBalance){
        this.previousBalance = previousBalance;
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

    public boolean isValidWithdrawalForProduct(String productType) {
        boolean isWithdrawalNameValid = investor.getProducts().stream()
        .anyMatch(product -> withdrawalName.equals(product.getName()));

        if (!isWithdrawalNameValid) {
            return false;
        }

        Product selectedProduct = investor.getProducts().stream()
                .filter(product -> productType.equals(product.getType()))
                .findFirst()
                .orElse(null);

        if (selectedProduct == null) {
            return false;
        }

        if (withdrawalAmount > selectedProduct.getCurrentBalance()) {
            return false;
        }

        return true;
    }
    public boolean isValidWithdrawalForProductRetirement(String productType) {
        Product selectedProduct = investor.getProducts().stream()
        .filter(product -> productType.equals(product.getType()))
        .findFirst()
        .orElse(null);

        if (selectedProduct == null) {
            return false;
        }

        if ("Retirement".equals(selectedProduct.getType()) && investor.getAge() < 65) {
            return false;
        }

        return true;
    }
    
}