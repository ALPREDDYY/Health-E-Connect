package com.hc.tcp_backend.controller;
import com.hc.tcp_backend.clientmodels.Profile;
import com.hc.tcp_backend.model.PatientDetails;
import com.hc.tcp_backend.repository.PatientDetailsRepository;
import com.hc.tcp_backend.service.DoctorDetailsService;
import com.hc.tcp_backend.service.PatientLoginService;

import com.hc.tcp_backend.service.PatientsDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PatientController {

    @Autowired
    DoctorDetailsService doctor_service;
    @Autowired
    PatientLoginService patient_service;
   @Autowired
   PatientDetailsRepository patientDetailsRepository;
    @Autowired
    PatientsDetailsService patient_details_service;


    @GetMapping("/profiles/{phone_number}")
    public ResponseEntity<List<PatientDetails>> get_profiles(@PathVariable("phone_number") String phone_number){
        try {
            System.out.println("inside");
            List<PatientDetails> lt;
            System.out.println("fdfdfdd");
            lt = patient_service.userprofiles(phone_number);

            List<PatientDetails> sending_lst = new ArrayList<>();

            for (int i = 0; i < lt.size(); i++) {
                PatientDetails patient = lt.get(i);

                if (patient.isActive() == true)
                    sending_lst.add(patient);
            }

            return new ResponseEntity<>(sending_lst, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("profiles/pin")
    public ResponseEntity<PatientDetails> pass(@RequestParam int patient_id){
        PatientDetails ud= patient_service.userprofilepass(patient_id);
        
        if (ud!=null){
            System.out.println(ud);
            return new ResponseEntity<>(ud,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }

   @Transactional
    @PostMapping("/PostProfileDetails")
    public ResponseEntity<String> addProfile(@RequestBody Profile profile){
        System.out.println("inside post");
        try{
            PatientDetails patientDetailsObj = new PatientDetails(profile.getName(),profile.getDob(), profile.getGender(), profile.getPhone_number(), profile.getLocation(), true, profile.getPin());
            patient_details_service.save(patientDetailsObj);
            return new ResponseEntity<>("Success",HttpStatus.OK);


        }catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    @DeleteMapping("/DeleteProfile/{patientId}")
    public ResponseEntity<String> deleteProfiles(@PathVariable("patientId") int patientId) {
        try
        {
            PatientDetails patient = patient_details_service.findById(patientId);
            int id_patient=patient.getPatientId();
            patientDetailsRepository.updatepatientdetails(id_patient);

            return new ResponseEntity<>("Success", HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
