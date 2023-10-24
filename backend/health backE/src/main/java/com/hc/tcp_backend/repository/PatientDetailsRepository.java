package com.hc.tcp_backend.repository;

import com.hc.tcp_backend.model.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientDetailsRepository extends JpaRepository<PatientDetails, String> {

    @Query(value = "SELECT patient_id,cast(AES_DECRYPT(from_base64(patient_name), 'mykeystring') AS char) as patient_name,patient_dob,gender,cast(AES_DECRYPT(from_base64(patient_phone_number), 'mykeystring') AS char) as patient_phone_number,cast(AES_DECRYPT(from_base64(patient_location), 'mykeystring') AS char) as patient_location,is_active,cast(AES_DECRYPT(from_base64(patient_pin), 'mykeystring') AS char) as patient_pin FROM patient_details WHERE patient_phone_number = cast(to_base64(AES_ENCRYPT(:phone_number ,'mykeystring')) as char)", nativeQuery = true)
    List<PatientDetails> getuserprofiles(@Param("phone_number") String phone_number);
    @Query(value = "SELECT patient_id,cast(AES_DECRYPT(from_base64(patient_name), 'mykeystring') AS char) as patient_name,patient_dob,gender,cast(AES_DECRYPT(from_base64(patient_phone_number), 'mykeystring') AS char) as patient_phone_number,cast(AES_DECRYPT(from_base64(patient_location), 'mykeystring') AS char) as patient_location,is_active,cast(AES_DECRYPT(from_base64(patient_pin), 'mykeystring') AS char) as patient_pin FROM patient_details where patient_id = :patient_id",nativeQuery = true)
    PatientDetails getuserprofilepass(@Param("patient_id") int patient_id);
    @Modifying
    @Query(value = "update patient_details set is_active = false where patient_id = :patient_id ",nativeQuery = true)
    void updatepatientdetails(@Param("patient_id") int patient_id);
    @Query(value = "SELECT patient_id,cast(AES_DECRYPT(from_base64(patient_name), 'mykeystring') AS char) as patient_name,patient_dob,gender,cast(AES_DECRYPT(from_base64(patient_phone_number), 'mykeystring') AS char) as patient_phone_number,cast(AES_DECRYPT(from_base64(patient_location), 'mykeystring') AS char) as patient_location,is_active,cast(AES_DECRYPT(from_base64(patient_pin), 'mykeystring') AS char) as patient_pin FROM patient_details WHERE patient_id = :id", nativeQuery = true)
    PatientDetails findByPatientId(@Param("id") int id);

    @Query(value = "SELECT patient_id,cast(AES_DECRYPT(from_base64(patient_name), 'mykeystring') AS char) as patient_name,patient_dob,gender,cast(AES_DECRYPT(from_base64(patient_phone_number), 'mykeystring') AS char) as patient_phone_number,cast(AES_DECRYPT(from_base64(patient_location), 'mykeystring') AS char) as patient_location,is_active,cast(AES_DECRYPT(from_base64(patient_pin), 'mykeystring') AS char) as patient_pin FROM patient_details WHERE patient_phone_number = cast(to_base64(AES_ENCRYPT(:ph_no ,'mykeystring')) as char)", nativeQuery = true)
    List<PatientDetails> findByPhoneNumber(@Param("ph_no") String ph_no);

    @Modifying
    @Query(value = "INSERT INTO patient_details (patient_name,patient_dob,gender,patient_phone_number,patient_location,is_active,patient_pin) VALUES(cast(to_base64(AES_ENCRYPT(:patient_name ,'mykeystring')) as char),:patient_dob,:gender, cast(to_base64(AES_ENCRYPT(:patient_phone_number ,'mykeystring')) as char),cast(to_base64(AES_ENCRYPT(:patient_location ,'mykeystring')) as char),:is_active,cast(to_base64(AES_ENCRYPT(:patient_pin ,'mykeystring')) as char))",nativeQuery = true)
    void addpatient(@Param("patient_name") String patient_name, @Param("patient_dob") Date patient_dob,@Param("gender") String gender,@Param("patient_phone_number") String patient_phone_number,@Param("patient_location") String patient_location,@Param("is_active") boolean is_active,@Param("patient_pin") String patient_pin);






}