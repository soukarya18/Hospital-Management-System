package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.model.Doctor;
import com.example.HospitalManagementSystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    /*
    * Doctor Management
        Register doctor
        View doctor by ID or name
         View all doctors
         Assign doctor to department
        Get list of patients under a doctor
      */
    @Autowired
    DoctorService doctorService ;


    // add a new doctor
    @PostMapping("add")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor) ;
    }

    // viewing all doctors (given by a specific department)
    @GetMapping("viewAll/{department}")
    public ResponseEntity<List<Doctor>>viewAllDoctors(@PathVariable String department){
        return doctorService.viewAll(department) ;
    }

    // update a specific doctor's details and also showing the updated details
    @PutMapping("update")
    public ResponseEntity<Doctor>updateDoctor(@RequestParam int id , @RequestBody Doctor updatedDoctor){
        return doctorService.updateDoctor(id , updatedDoctor) ;
    }

    // removing a doctor , and his patients will be getting a new appointment automatically
    // they can check their appointment details via ( http://localhost:8090/patient/view?id=? )
    @DeleteMapping("delete")
    public ResponseEntity<String>deleteDoctor(@RequestParam int id){
        return doctorService.deleteDoctor(id) ;
    }


}
