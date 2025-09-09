package com.example.HospitalManagementSystem.controller;

import com.example.HospitalManagementSystem.model.Patient;
import com.example.HospitalManagementSystem.model.PatientDetails;
import com.example.HospitalManagementSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patient")
public class PatientController {

    @Autowired
    PatientService patientService ;

    @GetMapping("home")
    // opening the home page
    public String home(){
        return "Welcome to Hospital service portal" ;
    }

    @PostMapping("register")
    // Patients can register according to their illness
    public String addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient) ;
    }

    @PostMapping("register/{department}/{doctor_name}")
    // Patient can register to a specific doctor directly via doctor name
    public String addPatientToDoctorName(@PathVariable String department , @PathVariable String doctor_name , @RequestBody Patient patient){
        return patientService.addPatientToDoctorName(department , doctor_name , patient) ;
    }

    @GetMapping("view")
    // Patients can view their appointment details via their patient_id as
    // which doctor has been appointed to them
    public PatientDetails viewPatient(@RequestParam int id){
       return   patientService.getPatientDetailsById(id) ;
    }

    @PutMapping("update")
    // patients can update their appointment via their patient_id and sending updated_details
    // can take back previous appointment with one doctor and change it to different department's doctor
    public PatientDetails updatePatient(@RequestParam int id ,@RequestBody Patient updatedPatient){
        return patientService.updatePatient(id  , updatedPatient) ;
    }

    // remove a patient
    @DeleteMapping("cancel")
    public String deletePatient(@RequestParam int id){
        return patientService.deletePatient(id) ;
    }






}
