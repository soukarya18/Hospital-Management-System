package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.model.Doctor;
import com.example.HospitalManagementSystem.model.Patient;
import com.example.HospitalManagementSystem.repo.DoctorRepo;
import com.example.HospitalManagementSystem.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DoctorService {

    @Autowired
    DoctorRepo doctorRepo ;

    @Autowired
    PatientService patientService ;
    public ResponseEntity<String> addDoctor(Doctor doctor) {
        doctorRepo.save(doctor) ;
        return new ResponseEntity<>("new doctor added "+doctor , HttpStatus.CREATED) ;
    }

    public ResponseEntity<List<Doctor>> viewAll(String dept) {
        List<Doctor> doctors = doctorRepo.findByDepartment(dept) ;
        return new ResponseEntity<>( doctors  , HttpStatus.OK);
    }

    public ResponseEntity<Doctor> updateDoctor(int id, Doctor updatedDoctor) {
        Doctor doctor= doctorRepo.findById(id).get() ;
        doctor.setDoctorName(updatedDoctor.getDoctorName());
        doctor.setDepartment(updatedDoctor.getDepartment());
        doctor.setExperience(updatedDoctor.getExperience());
        doctor.setPatients(updatedDoctor.getPatients());

        doctorRepo.save(doctor) ;
        return new ResponseEntity<>(doctor , HttpStatus.OK) ;
    }

    public ResponseEntity<String> deleteDoctor(int id) {
        Doctor doctor= doctorRepo.findById(id).get() ;
        List<Patient>patients= doctor.getPatients() ;
        // have to remove this from this doctor's patient list and storing them in a newList

        List<Patient> newList = new ArrayList<>();

        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()) {
            Patient p = iterator.next();
            newList.add(p);     // add to new list
            iterator.remove();  // remove from old list safely
        }

        //now delete the doctor
        doctorRepo.deleteById(id);

        // then allot them new doctors
        for(Patient patient:newList){
            patientService.addPatient(patient) ;
        }
        return new ResponseEntity<>(doctor.getDoctorName()+" with id "+id+" is removed successfully..." , HttpStatus.OK) ;
    }
}
