package com.hc.tcp_backend.JwtUtil;

import java.util.ArrayList;
import java.util.List;

import com.hc.tcp_backend.repository.DoctorLoginRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final DoctorLoginRepository pr;
    public JwtUserDetailsService(DoctorLoginRepository  pr){
        this.pr = pr;
    }
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        authList.add(new SimpleGrantedAuthority("verifyOTP"));
        User userByName = new User(mobile, "123456", authList);
        return new org.springframework.security.core.userdetails.User(userByName.getUsername(), userByName.getPassword(), userByName.getAuthorities());
    }

    public UserDetails loadDoctorByUsername(String username,String password) throws UsernameNotFoundException {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        authList.add(new SimpleGrantedAuthority("verifyPass"));
        User userByName = new User(username,password, authList);
        return new org.springframework.security.core.userdetails.User(userByName.getUsername(), userByName.getPassword(), userByName.getAuthorities());
    }
    public UserDetails loadAdminByUsername(String username,String password) throws UsernameNotFoundException {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
        authList.add(new SimpleGrantedAuthority("verifyPass"));
        User userByName = new User(username,password, authList);
        return new org.springframework.security.core.userdetails.User(userByName.getUsername(), userByName.getPassword(), userByName.getAuthorities());
    }
}