package com.example.HospitalManagementSystem;

import com.example.HospitalManagementSystem.service.SymptomAnalysisService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HospitalManagementSystemApplication {

	public static void main(String[] args)  {
		SpringApplication.run(HospitalManagementSystemApplication.class, args);
		System.out.println("server is ready...");
//		String str="Severe stomach pain and indigestion" ;
//		SymptomAnalysisService service= new SymptomAnalysisService() ;
//		String departmentToAlot = service.getDepartmentFromSymptoms(str) ;
//		System.out.println(departmentToAlot);
	}

}
