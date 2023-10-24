package com.hc.tcp_backend.service;

import com.hc.tcp_backend.model.DoctorLogin;
import com.hc.tcp_backend.repository.DoctorLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorLoginService {

    @Autowired
    private DoctorLoginRepository repo;

    public DoctorLogin findById(int id)
    {
        return repo.findByDoctorId(id);
    }

    public DoctorLogin findByemail(String username)
    {
        return repo.findByDoctorEmail(username);
    }

    public DoctorLogin save(DoctorLogin movement) {
        System.out.println("Before" + movement.getDoctorEmailId());
       // repo.save(movement);
        repo.adddoctorLogin(movement.getDoctorEmailId(),movement.getDoctorPassword(),movement.getDoctorId(),movement.getIsDoctorActive());
        System.out.println("After"+ movement.getDoctorId());
        return movement;
    }

    public List<DoctorLogin> getDoctors()
    {
        return repo.getDoctors();
    }

    public DoctorLogin getdoctorlogindetails(String email_id){
        return repo.findByDoctorEmail(email_id);
    }

}
