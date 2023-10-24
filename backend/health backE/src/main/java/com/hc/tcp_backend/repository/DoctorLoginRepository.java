package com.hc.tcp_backend.repository;

import com.hc.tcp_backend.model.DoctorLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorLoginRepository extends JpaRepository<DoctorLogin, String> {

    @Query(value = "SELECT cast(AES_DECRYPT(from_base64(doctor_email_id), 'mykeystring') AS char) as doctor_email_id,cast(AES_DECRYPT(from_base64(doctor_password), 'mykeystring') AS char) as doctor_password,doctor_id,is_doctor_active FROM doctor_login WHERE doctor_id = :id", nativeQuery = true)
    DoctorLogin findByDoctorId(@Param("id") int id);


    @Query(value = "SELECT cast(AES_DECRYPT(from_base64(doctor_email_id), 'mykeystring') AS char) as doctor_email_id, cast(AES_DECRYPT(from_base64(doctor_password), 'mykeystring') AS char) as doctor_password, doctor_id, is_doctor_active FROM doctor_login WHERE doctor_email_id = cast(to_base64(AES_ENCRYPT(:email_id, 'mykeystring')) AS char)", nativeQuery = true)
    DoctorLogin findByDoctorEmail( @Param("email_id") String email_id);

    @Modifying
    @Query(value = "update doctor_login set is_doctor_active=false where doctor_id=:doc_id",nativeQuery = true)
    void updatedoctorlogindetails(@Param("doc_id") int doc_id);

    @Query(value = "SELECT cast(AES_DECRYPT(from_base64(doctor_email_id), 'mykeystring') AS char) as doctor_email_id,cast(AES_DECRYPT(from_base64(doctor_password), 'mykeystring') AS char) as doctor_password,doctor_id,is_doctor_active FROM doctor_login", nativeQuery = true)
    List<DoctorLogin> getDoctors();
    @Modifying
    @Query(value = "INSERT INTO doctor_login (doctor_email_id, doctor_password, doctor_id, is_doctor_active) VALUES (cast(to_base64(AES_ENCRYPT(:doctor_email_id ,'mykeystring')) as char), cast(to_base64(AES_ENCRYPT(:doctor_password ,'mykeystring')) as char), :doctor_id, :is_doctor_active)", nativeQuery = true)
    void adddoctorLogin(@Param("doctor_email_id") String doctor_email_id, @Param("doctor_password") String doctor_password, @Param("doctor_id") int doctor_id, @Param("is_doctor_active") boolean is_doctor_active);


}
