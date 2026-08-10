package com.example.patientservice.service;

import com.example.patientservice.PatientServiceApplication;
import com.example.patientservice.dto.PatientRequestDTO;
import com.example.patientservice.dto.PatientResponseDTO;
import com.example.patientservice.mapper.PatientMapper;
import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();

        return patients.stream().
                map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatientDTO(PatientRequestDTO patientRequestDTO){
        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO)
        );

        return PatientMapper.toDTO(newPatient);
    }

}
