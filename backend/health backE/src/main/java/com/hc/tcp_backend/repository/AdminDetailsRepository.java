package com.hc.tcp_backend.repository;


import com.hc.tcp_backend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface AdminDetailsRepository extends JpaRepository<Admin, String> {

    @Query(value = "SELECT admin_id,cast(AES_DECRYPT(from_base64(admin_name), 'mykeystring') as char) as admin_name,admin_gender,admin_dob,cast(AES_DECRYPT(from_base64(admin_phone_number), 'mykeystring') as char) as admin_phone_number FROM admin WHERE admin_id = :id", nativeQuery = true)
    Admin findByAdminId(@Param("id") int id);

    @Query(value = "SELECT admin_id,cast(AES_DECRYPT(from_base64(admin_name), 'mykeystring') as char) as admin_name,admin_gender,admin_dob,cast(AES_DECRYPT(from_base64(admin_phone_number), 'mykeystring') as char) as admin_phone_number FROM admin", nativeQuery = true)
    List<Admin> getAdmins();
    @Query(value = "SELECT admin_id FROM admin ORDER BY admin_id DESC LIMIT 1",nativeQuery = true)
    int lastadmindetails();
    @Modifying
    @Query(value = "INSERT INTO admin (admin_name, admin_gender, admin_dob, admin_phone_number) VALUES (cast(to_base64(AES_ENCRYPT(:admin_name, 'mykeystring')) as char), :admin_gender, :admin_dob, cast(to_base64(AES_ENCRYPT(:admin_phone_number, 'mykeystring')) as char))",nativeQuery = true)
    void addadmin(@Param("admin_name") String admin_name, @Param("admin_gender") String admin_gender, @Param("admin_dob") Date admin_dob, @Param("admin_phone_number") String admin_phone_number);


//    @Query(value = "INSERT INTO admin (admin_name, admin_gender, admin_dob, admin_phone_number) VALUES (cast(to_base64(AES_ENCRYPT(:admin_name, 'mykeystring')) as char), :admin_gender, :admin_dob, cast(to_base64(AES_ENCRYPT(:admin_phone_number, 'mykeystring')) as char))")
//    void addadmin(@Param("admin_name") String admin_name, @Param("admin_gender") String admin_gender, @Param("admin_dob") Date admin_dob, @Param("admin_phone_number") String admin_phone_number);

}
