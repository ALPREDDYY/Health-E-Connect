package com.hc.tcp_backend.controller;
import com.hc.tcp_backend.clientmodels.Doctor;
import com.hc.tcp_backend.clientmodels.DoctorAvailable;
import com.hc.tcp_backend.model.DoctorDetails;
import com.hc.tcp_backend.model.DoctorLogin;
import com.hc.tcp_backend.repository.DoctorDetailsRepository;
import com.hc.tcp_backend.repository.DoctorLoginRepository;
import com.hc.tcp_backend.JwtUtil.TokenManager;
import com.hc.tcp_backend.JwtUtil.models.JwtResponseModel;
import com.hc.tcp_backend.JwtUtil.JwtUserDetailsService;
import com.hc.tcp_backend.service.DoctorDetailsService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import com.hc.tcp_backend.service.DoctorLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class DoctorController {
    @Autowired
    DoctorDetailsService doctor_service;
	@Autowired
	TokenManager tokenManager;
	@Autowired
	DoctorLoginService doctor_login_service;
    @Autowired
	JwtUserDetailsService userDetailsService;
	@Autowired
    DoctorDetailsRepository doctorDetailsRepository;

  @Autowired
  DoctorLoginRepository doctorLoginRepository;
	@Transactional
    @PutMapping("/DoctorAvailability")
    public ResponseEntity<Integer> change_status(@RequestBody DoctorAvailable doctor) {
        try {
            String ans = "";
            int updatedRows = doctorDetailsRepository.updatedoctordetails(doctor.getDoctorId(), doctor.isStatus());
            if (updatedRows > 0) {
            ans = "Success";
            System.out.println(ans);
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            ans = "Failed to update doctor status";
            System.out.println(ans);
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        } catch (Exception e) {
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


	@GetMapping("/Doctor")
	public ResponseEntity Doctorlogin(@RequestParam String username,@RequestParam String password){
		DoctorLogin s = doctor_login_service.findByemail(username);
		if(s == null){
			return ResponseEntity.ok(new JwtResponseModel("not"));
		}
		if (!s.getIsDoctorActive())
			return ResponseEntity.ok(new JwtResponseModel("not"));
		if(s!=null && s.getDoctorPassword().equals(password)) {
			System.out.println("entered");
			final UserDetails userDetails = userDetailsService.loadDoctorByUsername(username,password);

			final String jwtToken = tokenManager.generateJwtToken(userDetails);
			System.out.println("hellso");
			//System.out.println(jwtToken);
			//System.out.println());
			return ResponseEntity.ok(new JwtResponseModel(jwtToken));
		}
		return ResponseEntity.ok(new JwtResponseModel("not"));
	}
	@GetMapping("/doctordetails")
	public ResponseEntity<DoctorDetails> getdoctordetails(@RequestParam String email_id){
		DoctorLogin dl=doctor_login_service.getdoctorlogindetails(email_id);
		System.out.println("hit");
		DoctorDetails dd=doctorDetailsRepository.findByDoctorId(dl.getDoctorId());
		if (dd!=null){
			return new ResponseEntity<>(dd,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	}


   @Transactional
	@PostMapping ("/PostDoctorDetails")
	public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
		try {

			String a = "";
			int flag = 1;
			List<DoctorLogin> doctorLogins = doctor_login_service.getDoctors();
			System.out.println("asdfhj");
			for(int i=0; i<doctorLogins.size(); i++)
			{
				if(doctorLogins.get(i).getDoctorEmailId().equalsIgnoreCase(doctor.getEmail_id())){
					flag = 0;
					break;
				}
			}

			if(flag==1)
			{

				DoctorDetails doctorDetails = new DoctorDetails(doctor.getName(), doctor.getGender(), doctor.getDob(),doctor.getDepartment_name(),doctor.getQualification(),doctor.getClinic_address(), doctor.getPhone_number(), false, doctor.getDoctor_start_date());
				doctorDetails = doctor_service.save(doctorDetails);
				DoctorLogin doctorLogin = new DoctorLogin(doctor.getEmail_id(), doctor.getPassword(), doctorDetails.getDoctorId(), true);
                System.out.println(doctorLogin.getDoctorId());
				doctorLogin = doctor_login_service.save(doctorLogin);
				a = "Success";
			}
			else
				a = "Failed. Email Id is already existing.";

			return new ResponseEntity<>(a, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
    @GetMapping("/GetDoctorsList")
    public ResponseEntity<List<Doctor>> sendDoctorList(){
    	
    	try {
    		//System.out.println("HI");
    		List<Doctor> doctors = new ArrayList<Doctor>();
			System.out.println("hii");
    		List<DoctorDetails> doctorDetails = doctor_service.getDoctors();
    		
    		System.out.println(doctorDetails.toString());
    		
    		for (int i=0 ; i<doctorDetails.size() ; i++)
    		{
    			DoctorDetails singleDoctorDetails = doctorDetails.get(i);

    			String name = singleDoctorDetails.getDoctorName();
				int id = singleDoctorDetails.getDoctorId();
    		    Date dob = singleDoctorDetails.getDoctorDob();
    		    String gender = singleDoctorDetails.getDoctorGender();
    		    String email_id = "nobody@gmail.com";   
    		    Date doctor_start_date = singleDoctorDetails.getDoctorStartDate();
    		    String qualification = singleDoctorDetails.getDoctorQualification();
    			String department_name = singleDoctorDetails.getDepartmentName();
    		    String phone_number = singleDoctorDetails.getDoctorPhoneNumber();
    		    String clinic_address = singleDoctorDetails.getDoctorClinicAddress();
    		    String password = "qwertyjhgsaADGNN";

				DoctorLogin docLogin = doctor_login_service.findById(id);
				if(docLogin.getIsDoctorActive()==true){
					Doctor doctor = new Doctor(id,name,dob,gender,email_id,doctor_start_date,
							qualification,department_name, phone_number, clinic_address, password);

					doctors.add(doctor);
				}

    		}
    		
    		for(int i=0 ; i<doctors.size() ; i++)
    		{
    			//System.out.println(doctors.get(i).toString());
    		}
    		
    		return new ResponseEntity<>(doctors, HttpStatus.OK);
    		
    	}
    	
    	catch (Exception e)
    	{
    		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
		 	
    	
    }
    @Transactional
	@DeleteMapping("/DeleteDoctor")
	public ResponseEntity<String> deleteDoctor(@RequestParam int doctorId)
	{
		System.out.println("Inside");
		try
		{
			DoctorLogin doctorLoginDetails = doctor_login_service.findById(doctorId);
			//doctorLoginDetails.setIsDoctorActive(false);
			System.out.println(doctorLoginDetails.getDoctorId());
			doctorLoginRepository.updatedoctorlogindetails(doctorLoginDetails.getDoctorId());
			//doctor_login_service.save(doctorLoginDetails);

			return new ResponseEntity<>("Success", HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
