package com.example.viking.tsx6;

import java.io.Serializable;

/**
 * Created by viking on 19/8/16.
 */
public class for_register implements Serializable {
    String f_name,l_name,clg,country,contact;
    String email;
    String password;

    public for_register(String fname,String lname,String clg,String country,String contact,String email,String password)
    {
        super();
        this.f_name=fname;
        this.l_name=lname;
        this.clg=clg;
        this.country=country;
        this.contact=contact;
        this.email=email;
        this.password=password;

    }


    public String getF_name() {
        return f_name;
    }
    public String getL_name() {
        return l_name;
    }
    public String getClg() {
        return clg;
    }
    public String getCountry() {
        return country;
    }
    public String getContact() {
        return contact;
    }
    public String getUsername()
    {
        return email;
    }
    public String getPassword()
    {
        return password;
    }


}
