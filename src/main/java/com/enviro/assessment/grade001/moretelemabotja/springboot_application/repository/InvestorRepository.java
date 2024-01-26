package com.enviro.assessment.grade001.moretelemabotja.springboot_application.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Investor;

public interface InvestorRepository extends JpaRepository<Investor, Long>{
}


