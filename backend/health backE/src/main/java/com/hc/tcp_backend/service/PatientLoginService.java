package com.hc.tcp_backend.service;

import com.hc.tcp_backend.model.PatientDetails;
import com.hc.tcp_backend.repository.PatientDetailsRepository;
import com.twilio.rest.verify.v2.service.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.verify.v2.service.VerificationCheck;

import java.util.List;
import java.util.Random;

@Service
public class PatientLoginService {
    private TwilioRestClient twilioRestClient;
    @Autowired
    private PatientDetailsRepository patientDetailsRepository;

    public String generateOTP(String phoneNumber) {
        // Generate a random 6-digit OTP
        System.out.println(phoneNumber+"Service");
        String otp = String.format("%06d", new Random().nextInt(999999));

        System.out.println("Start");
        // Send the OTP to the user's phone via SMS
        final String ACCOUNT_SID = "ACef9fd25cd8869b1bf04ff8e4fa3fee96";
        final String AUTH_TOKEN = "6d4131b0ae8732ff76b1094f62b1253d";
        final String FROM_NUMBER = "+15134297417";

        System.out.println("before");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Verification verification = Verification.creator("VA822a4b4eb5b90d416b978c901c12b433","+1"+phoneNumber,"sms").create();
        System.out.println(verification.getStatus());
        System.out.println("after");

        return verification.getStatus();
    }

    public String verifyOTP(String phoneNumber, String enteredOTP) {
        // Verify the OTP entered by the user
        final String ACCOUNT_SID = "ACef9fd25cd8869b1bf04ff8e4fa3fee96";
        final String AUTH_TOKEN = "6d4131b0ae8732ff76b1094f62b1253d";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        VerificationCheck verification;
        try {
            verification = VerificationCheck.creator("VA822a4b4eb5b90d416b978c901c12b433").setTo("+1" + phoneNumber).setCode(enteredOTP).create();
        }catch (Exception e){
            return "decline";
        }
        return verification.getStatus();
    }
    public List<PatientDetails> userprofiles(String phone_number){
        return patientDetailsRepository.getuserprofiles(phone_number);
    }
    public PatientDetails userprofilepass(int patient_id){
        System.out.println("reached");
        PatientDetails u= patientDetailsRepository.getuserprofilepass(patient_id);
        System.out.println(u);
        return u;
    }


}


