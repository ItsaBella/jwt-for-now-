package com.lubanzi.qlitygigs.Model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class JwtRequest implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    private String uEmail;
	private String uPassword;
	
	public JwtRequest(){

	}

	public JwtRequest(String email, String password) {
		this.uEmail = email;
		this.uPassword = password;
	}

	public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

	public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }
    
}