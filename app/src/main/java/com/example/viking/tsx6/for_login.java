package com.example.viking.tsx6;

import java.io.Serializable;

/**
 * Created by viking on 17/8/16.
 */
public class for_login implements Serializable {
    String username;
    String password;

    public for_login(String username,String password)
    {
        super();
        this.username=username;
        this.password=password;

    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }


}

