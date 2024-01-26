package com.enviro.assessment.grade001.moretelemabotja.springboot_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.WithdrawalNotice;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Product;


public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long>{
    
}
