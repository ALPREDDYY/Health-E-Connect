package com.hc.tcp_backend.service;


import com.hc.tcp_backend.model.Admin;
import com.hc.tcp_backend.repository.AdminDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminDetailsService {
    @Autowired
    private AdminDetailsRepository repo;
    public Admin findById(int id)
    {
        return repo.findByAdminId(id);
    }

    public Admin save(Admin movement) {
        //System.out.println(movement.getDoctorId());
        //repo.save(movement);
        repo.addadmin(movement.getAdminName(),movement.getAdminGender(),movement.getAdminDob(),movement.getAdminPhoneNumber());
        //System.out.println(movement.getDoctorId());
        int id= repo.lastadmindetails();
        System.out.println(id);
        return repo.findByAdminId(id);
    }

    public List<Admin> getAdminList()
    {
        return repo.getAdmins();
    }

}
