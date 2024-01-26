package com.enviro.assessment.grade001.moretelemabotja.springboot_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.service.WithdrawalNoticeService;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto.WithdrawalNoticeDto;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto.InvestorDto;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.dto.ProductDto;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Investor;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Product;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;

@Component
public class UserCommandLine implements CommandLineRunner {

    private final WithdrawalNoticeService withdrawalNoticeService;

    @Autowired
    public UserCommandLine(WithdrawalNoticeService withdrawalNoticeService) {
        this.withdrawalNoticeService = withdrawalNoticeService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Get Investor Details");
            System.out.println("2. Get Investor Products");
            System.out.println("3. Create Withdrawal Notice");
            System.out.println("4. Exit");
            System.out.print("Please choose an option: ");

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }

            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                    System.out.print("Enter Investor ID: ");
                    Long investorId = scanner.nextLong();
                    InvestorDto investorDto = withdrawalNoticeService.getInvestorDetails(investorId);
                    System.out.println("Investor Details: " + investorDto);
                    break;
                    case 2:
                    System.out.print("Enter Investor ID: ");
                    Long investorIdProducts = scanner.nextLong();
                    List<ProductDto> products = withdrawalNoticeService.getInvestorProducts(investorIdProducts);
                    System.out.println("Investor Products: " + products);
                    break;
                    case 3:
                    WithdrawalNoticeDto withdrawalNoticeDto = new WithdrawalNoticeDto();
                    System.out.print("Please Enter Name Of Product From Which You Would Like To Withdraw: ");
                    withdrawalNoticeDto.setProductName(scanner.next());
                    System.out.print("Enter Withdrawal Amount: ");
                    withdrawalNoticeDto.setWithdrawalAmount(scanner.nextDouble());
                    System.out.print("Enter Bank Name: ");
                    withdrawalNoticeDto.setBankName(scanner.next());
                
                    System.out.print("Enter Account Holder Name: ");
                    withdrawalNoticeDto.setAccountHolderName(scanner.next());
                
                    System.out.print("Enter Account Number: ");
                    withdrawalNoticeDto.setAccountNumber(scanner.next());
                
                    System.out.print("Enter Branch Code: ");
                    withdrawalNoticeDto.setBranchCode(scanner.next());
                    withdrawalNoticeDto.setWithdrawalDate(LocalDate.now());
                    String result = withdrawalNoticeService.createWithdrawalNotice(withdrawalNoticeDto);
                    System.out.println(result);
                    break;

                    case 4:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option. Please choose again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'exit'.");
            }
        }
    }
}
