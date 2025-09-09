package com.example.HospitalManagementSystem.service;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@Service
public class SymptomAnalysisService {


    public String getDepartmentFromSymptoms(String symptoms)  {

            symptoms = symptoms.toLowerCase();

            if (symptoms.contains("chest") || symptoms.contains("breath") || symptoms.contains("heart")) {
                return "Cardiology";
            } else if (symptoms.contains("headache") || symptoms.contains("seizure") || symptoms.contains("memory")) {
                return "Neurology";
            } else if (symptoms.contains("joint") || symptoms.contains("bone") || symptoms.contains("fracture")) {
                return "Orthopedics";
            } else if (symptoms.contains("stomach") || symptoms.contains("vomit") || symptoms.contains("liver")) {
                return "Gastroenterology";
            } else if (symptoms.contains("skin") || symptoms.contains("rash") || symptoms.contains("acne")) {
                return "Dermatology";
            } else if (symptoms.contains("eye") || symptoms.contains("vision") || symptoms.contains("blur")) {
                return "Eye Specialist";
            } else if (symptoms.contains("pregnant") || symptoms.contains("menstrual") || symptoms.contains("uterus")) {
                return "Gynecology";
            } else {
                return "Medicine"; // default/general physician
            }


    }
}
