package com.hc.tcp_backend.service;


import com.hc.tcp_backend.model.AdminLogin;
import com.hc.tcp_backend.repository.AdminLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdminLoginService {

    @Autowired
    private AdminLoginRepository repo;

    public AdminLogin findById(int id)
    {
        return repo.findByAdminId(id);
    }

    public AdminLogin findByemail(String email_id)
    {
        System.out.println(email_id);
        AdminLogin al=repo.findByAdminEmail(email_id);
        System.out.println(al);
        return al;
    }

    public AdminLogin save(AdminLogin movement) {
        System.out.println("Before" + movement.getAdminEmailId());
        //repo.save(movement);
        repo.addadmin(movement.getAdminEmailId(),movement.getAdminPassword(),movement.getAdminId(),movement.getIsAdminActive());
        System.out.println("After"+ movement.getAdminId());
        return movement;
    }
    public AdminLogin getadminlogindetails(String email_id){
        return repo.findByAdminEmail(email_id);
    }
    public List<AdminLogin> getAdmins()
    {
        return repo.getAdmins();
    }
}
