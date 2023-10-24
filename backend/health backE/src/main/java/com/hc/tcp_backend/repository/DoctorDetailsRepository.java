package com.hc.tcp_backend.repository;
import com.hc.tcp_backend.model.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DoctorDetailsRepository extends JpaRepository<DoctorDetails, String>{
    @Query(value = "SELECT DISTINCT doctor_id,cast(AES_DECRYPT(from_base64(doctor_name), 'mykeystring') AS char) as doctor_name, doctor_dob, doctor_gender, cast(AES_DECRYPT(from_base64(department_name), 'mykeystring') AS char) as department_name,cast(AES_DECRYPT(from_base64(doctor_qualification), 'mykeystring') AS char) as doctor_qualification,cast(AES_DECRYPT(from_base64(doctor_clinic_address), 'mykeystring') AS char) as doctor_clinic_address, cast(AES_DECRYPT(from_base64(doctor_phone_number), 'mykeystring') AS char) as doctor_phone_number, doctor_availability, doctor_start_date FROM doctor_details WHERE doctor_id = :id", nativeQuery = true)
    DoctorDetails findByDoctorId(@Param("id") int id);



    //@Query()
   
    @Query(value = "SELECT doctor_details.doctor_id,cast(AES_DECRYPT(from_base64(doctor_name), 'mykeystring') AS char) as doctor_name, doctor_dob, doctor_gender, cast(AES_DECRYPT(from_base64(department_name), 'mykeystring') AS char) as department_name,cast(AES_DECRYPT(from_base64(doctor_qualification), 'mykeystring') AS char) as doctor_qualification,cast(AES_DECRYPT(from_base64(doctor_clinic_address), 'mykeystring') AS char) as doctor_clinic_address, cast(AES_DECRYPT(from_base64(doctor_phone_number), 'mykeystring') AS char) as doctor_phone_number, doctor_availability, doctor_start_date FROM doctor_details INNER JOIN doctor_login ON doctor_details.doctor_id=doctor_login.doctor_id", nativeQuery = true)
    List<DoctorDetails> getDoctors();

    @Query(value = "SELECT doctor_id FROM doctor_details ORDER BY doctor_id DESC LIMIT 1",nativeQuery = true)
    int lastdoctordetails();

    @Modifying
    @Query(value = "update doctor_details set doctor_availability = :status where doctor_id = :doctor_id ",nativeQuery = true)
    void updatedoctordetails(@Param("doctor_id") int doctor_id,@Param("status") boolean status);


    @Modifying
    @Query(value = "INSERT INTO doctor_details (doctor_name, doctor_dob, doctor_gender, department_name, doctor_qualification, doctor_clinic_address, doctor_phone_number, doctor_availability, doctor_start_date) VALUES (cast(to_base64(AES_ENCRYPT(:doctor_name, 'mykeystring')) as char), :doctor_dob, :doctor_gender, cast(to_base64(AES_ENCRYPT(:department_name, 'mykeystring')) as char), cast(to_base64(AES_ENCRYPT(:doctor_qualification, 'mykeystring')) as char), cast(to_base64(AES_ENCRYPT(:doctor_clinic_address, 'mykeystring')) as char), cast(to_base64(AES_ENCRYPT(:doctor_phone_number, 'mykeystring')) as char), :doctor_availability, :doctor_start_date)", nativeQuery = true)
void adddoctor(@Param("doctor_name") String doctor_name, @Param("doctor_dob") Date doctor_dob, @Param("doctor_gender") String doctor_gender, @Param("department_name") String department_name, @Param("doctor_qualification") String doctor_qualification, @Param("doctor_clinic_address") String doctor_clinic_address, @Param("doctor_phone_number") String doctor_phone_number, @Param("doctor_availability") boolean doctor_availability, @Param("doctor_start_date") Date doctor_start_date);

}
