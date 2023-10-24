package com.hc.tcp_backend.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "admin_login")
public class AdminLogin {
    @Id
    @Column(name = "e")
    private String e;

    @Column(name = "p")
    private String p;

    @Column(name = "i")
    private int i;

    @Column(name = "a")
    private boolean a;

    public AdminLogin() {

    }

    public AdminLogin(String e, String p, int i, boolean a) {
        this.e = e;
        this.p = p;
        this.i = i;
        this.a = a;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public boolean getA() {
        return a;
    }

    public void setA(boolean a) {
        this.a = a;
    }
}
