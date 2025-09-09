package com.example.HospitalManagementSystem.repo;

import com.example.HospitalManagementSystem.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    List<Doctor> findByDepartment(String problem);
}
