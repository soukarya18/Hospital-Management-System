package com.example.HospitalManagementSystem.service;

import com.example.HospitalManagementSystem.model.Doctor;
import com.example.HospitalManagementSystem.model.Patient;
import com.example.HospitalManagementSystem.model.PatientDetails;
import com.example.HospitalManagementSystem.repo.DoctorRepo;
import com.example.HospitalManagementSystem.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepo patientRepo ;
    @Autowired
    DoctorRepo doctorRepo ;
    @Autowired
    SymptomAnalysisService symptomAnalysisService ;


    public String addPatient(Patient patient) {

        String problem = patient.getProblem() ;
        String departmentToAlot = symptomAnalysisService.getDepartmentFromSymptoms(problem) ;
        List<Doctor> doctors = doctorRepo.findByDepartment(departmentToAlot) ;
        patient.setProblem(departmentToAlot);
        boolean appointed=false ;
        Doctor appointedDoctor = null ;
        for(Doctor doctor:doctors){
            if(doctor.getPatients().size()<5){
                List<Patient>patients = doctor.getPatients() ;
                patients.add(patient) ;
                doctor.setPatients(patients);
                patientRepo.save(patient) ;
                appointed=true ;
                appointedDoctor=doctor ;

                break;
            }
        }
        if(appointed){
            return ("patient id: "+patient.getId()+" Mr./Mrs. "+patient.getName() +" has been appointed to "+appointedDoctor.getDoctorName()+ " in "+ appointedDoctor.getDepartment()) ;
        }


        return ("All bookings have been closed for today...." ) ;
    }




    public String addPatientToDoctorName(String department, String doctorName , Patient patient) {
        List<Doctor> doctors= doctorRepo.findAll() ;

        for(Doctor doctor : doctors){
            if(doctor.getDepartment().equalsIgnoreCase(department) && doctor.getDoctorName().equalsIgnoreCase(doctorName)){
//                patient.setDoctor(doctor);
                List<Patient> prev_list = doctor.getPatients() ;
                if(prev_list.size()<5) {
                    prev_list.add(patient);
                    doctor.setPatients(prev_list);
                    patientRepo.save(patient);

                    return ("patient details-> " + patient+" saved successfully" ) ;

                }
                else{
                    return (doctor.getDoctorName()+" has closed taking appointments for this day"+" You can book appointment to another doctor...");
                }
            }
        }

        return "Invalid data... please recheck" ;

    }





    public PatientDetails getPatientDetailsById(int id) {
        PatientDetails patientDetails=new PatientDetails() ;
        Patient patient = patientRepo.findById(id).get() ;
        String patientProblem = patient.getProblem() ;
//        String departmentToAlot = symptomAnalysisService.getDepartmentFromSymptoms(patientProblem) ;
        Doctor appointedDoctor =null ;
        List<Doctor> doctors = doctorRepo.findByDepartment(patientProblem);
        boolean found = false ;
        for(Doctor doctor:doctors){
            List<Patient>patients= doctor.getPatients() ;
            for(Patient p: patients){
                if(p.getId()==id){
                    appointedDoctor=doctor ;
                    found=true ;
                    break;
                }
            }
            if(found) break ;
        }
        if(!found) return patientDetails ;
        patientDetails.setId(id);
        patientDetails.setName(patient.getName());
        patientDetails.setSex(patient.getSex());
        patientDetails.setProblem(patient.getProblem());
        patientDetails.setAppointedDoctorName(appointedDoctor.getDoctorName());

        return patientDetails ;
}



    public PatientDetails updatePatient(int id , Patient updatedPatient) {
        PatientDetails patientDetails = new PatientDetails() ;

        Patient patient = patientRepo.findById(id).get() ;
        String previous_problem = patient.getProblem() ;

        // delete this patient from its existing appointment
        List<Doctor> doctors= doctorRepo.findByDepartment(previous_problem) ;

        for(Doctor doctor:doctors){
            List<Patient>patients = doctor.getPatients() ;
            for (int i = 0; i < patients.size(); i++) {
                if (patients.get(i).getId() == patient.getId()) {
                    patients.remove(i);
                    break;  // deleted
                }
            }
        }

        // update the particular patient with his current updated details
        patient.setName(updatedPatient.getName());
        patient.setSex(updatedPatient.getSex());
        patient.setProblem(updatedPatient.getProblem());

        // add him to suitable doctor
        addPatient(patient) ;

        // fetch his updated details after adding to a new doctor
       patientDetails = getPatientDetailsById(id) ;


        return patientDetails ;
    }

    public String deletePatient(int id) {
        // fetching the actual patient
        Patient patient = patientRepo.findById(id).get() ;

        // find his illness
        String problem = patient.getProblem() ;

        // get all doctors for that specific problem
        List<Doctor>doctors= doctorRepo.findByDepartment(problem) ;
        boolean removed = false ;

        for(Doctor doctor: doctors){
            List<Patient>patients = doctor.getPatients() ;
            for(int i=0;i<patients.size();i++) {
                if(patients.get(i).getId()==patient.getId()){
                    removed=true ;
                    patients.remove(i) ;// remove him from doctor's appointment
                    break ;
                }
            }
            if(removed) break ;
        }
        if(removed) {
            patientRepo.deleteById(id);
            return ("id " + id + " successfully deleted");
        }
        else{
            return ("invalid id given , please check ...") ;
        }
    }
}
