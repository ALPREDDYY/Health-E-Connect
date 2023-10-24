package com.hc.tcp_backend.service;

import com.hc.tcp_backend.model.PatientDetails;
import com.hc.tcp_backend.repository.PatientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class PatientsDetailsService {

        @Autowired
        private PatientDetailsRepository repo;
        public PatientDetails findById(int id)
        {
            return repo.findByPatientId(id);
        }

        public void save(PatientDetails movement) {
            System.out.println(movement.toString());
            //repo.save(movement);
            repo.addpatient(movement.getPatientName(), movement.getPatientDob(), movement.getGender(), movement.getPatientPhoneNumber(), movement.getPatientLocation(),true, movement.getPatientPin());
            System.out.println(movement.getPatientId());
            return ;
        }

        public List<PatientDetails> getProfiles(String phone_number)
        {
            return repo.findByPhoneNumber(phone_number);
        }
}
