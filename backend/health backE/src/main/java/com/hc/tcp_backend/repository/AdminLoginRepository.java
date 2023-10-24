package com.hc.tcp_backend.repository;

import com.hc.tcp_backend.model.AdminLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminLoginRepository extends JpaRepository<AdminLogin, String> {

    @Query(value = "SELECT cast(AES_DECRYPT(from_base64(admin_email_id), 'mykeystring') as char) as admin_email_id, cast(AES_DECRYPT(from_base64(admin_password), 'mykeystring') as char) as admin_password, admin_id, is_admin_active FROM admin_login WHERE admin_id = :id", nativeQuery = true)
    AdminLogin findByAdminId(@Param("id") int id);

//    @Query(value = "SELECT cast(AES_DECRYPT(from_base64(admin_email_id), 'mykeystring') as char) as admin_email_id,cast(AES_DECRYPT(from_base64(admin_password), 'mykeystring') as char) as admin_password,admin_id,is_admin_active FROM admin_login WHERE admin_email_id = cast(to_base64(AES_ENCRYPT(:email_id ,'mykeystring')) as char", nativeQuery = true)
//    AdminLogin findByAdminEmail(@Param("email_id") String email_id);
    @Query(value = "SELECT cast(AES_DECRYPT(from_base64(admin_email_id), 'mykeystring') as char) as admin_email_id, cast(AES_DECRYPT(from_base64(admin_password), 'mykeystring') as char) as admin_password, admin_id, is_admin_active FROM admin_login WHERE admin_email_id = cast(to_base64(AES_ENCRYPT(:email_id ,'mykeystring')) as char)", nativeQuery = true)
    AdminLogin findByAdminEmail(@Param("email_id") String email_id);
    @Modifying
    @Query(value = "update admin_login set is_admin_active=false where admin_id=:adm_id",nativeQuery = true)
    void updateadminlogindetails(@Param("adm_id") int adm_id);
    // value = "SELECT cast(AES_DECRYPT(from_base64(admin_email_id), 'mykeystring') as char) as admin_email_id,cast(AES_DECRYPT(from_base64(admin_password), 'mykeystring') as char) as admin_password,admin_id,is_admin_active FROM admin_login WHERE admin_email_id = :email_id", nativeQuery = true)
    @Query(value = "SELECT cast(AES_DECRYPT(from_base64(admin_email_id), 'mykeystring') as char) as admin_email_id,cast(AES_DECRYPT(from_base64(admin_password), 'mykeystring') as char) as admin_password,admin_id,is_admin_active FROM admin_login", nativeQuery = true)
    List<AdminLogin> getAdmins();
    @Modifying
    @Query(value = "INSERT INTO admin_login (admin_email_id,admin_password,admin_id,is_admin_active) VALUES(cast(to_base64(AES_ENCRYPT(:admin_email_id ,'mykeystring')) as char),cast(to_base64(AES_ENCRYPT(:admin_password ,'mykeystring')) as char),:admin_id,:is_admin_active)",nativeQuery = true)
    void addadmin(@Param("admin_email_id") String admin_email_id, @Param("admin_password") String admin_password, @Param("admin_id") int admin_id, @Param("is_admin_active") boolean is_admin_active);



}
