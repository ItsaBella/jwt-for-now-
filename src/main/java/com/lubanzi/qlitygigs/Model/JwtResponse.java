package com.lubanzi.qlitygigs.Model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final String jwt;

    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt(){
        return jwt;
    }
    
}