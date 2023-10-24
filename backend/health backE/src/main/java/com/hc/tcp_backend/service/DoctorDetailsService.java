package com.hc.tcp_backend.service;
import com.hc.tcp_backend.model.DoctorDetails;
import com.hc.tcp_backend.repository.DoctorDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorDetailsService {
    @Autowired
    private DoctorDetailsRepository repo;
    public DoctorDetails findById(int id)
    {
        return repo.findByDoctorId(id);
    }

    public DoctorDetails save(DoctorDetails movement) {
        //System.out.println(movement.getDoctorId());
        //repo.save(movement);
        System.out.println(movement.toString());
        repo.adddoctor(movement.getDoctorName(), movement.getDoctorDob(), movement.getDoctorGender(), movement.getDepartmentName(), movement.getDoctorQualification(), movement.getDoctorClinicAddress(), movement.getDoctorPhoneNumber(),true, movement.getDoctorStartDate());
        //System.out.println(movement.getDoctorId());--
        int id=repo.lastdoctordetails();
        return repo.findByDoctorId(id);
    }


    public List<DoctorDetails> getDoctors()
    {
    	return repo.getDoctors();
    }
    

}
