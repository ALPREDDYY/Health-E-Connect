package com.hc.tcp_backend.clientmodels;

public class DoctorAvailable {
    private int doctorId;
    private boolean available;

    public DoctorAvailable() {
    }

    public DoctorAvailable(int doctorId, boolean available) {
        this.doctorId = doctorId;
        this.available = available;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public boolean isStatus() {
        return available;
    }

    public void setStatus(boolean available) {
        this.available = available;
    }
}
