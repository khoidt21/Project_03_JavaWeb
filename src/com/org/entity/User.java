package com.org.entity;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class User {
	
	private String username;
    private String password;
    private ArrayList<String> errs;
    
    public User() {}
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        errs = new ArrayList();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getErrors() {
        return errs;
    }
    
    public void addError(String e){
        errs.add(e);
    }

    public boolean validationUser(){
    	// validation username
    	if(username.length() <=6) {
    		errs.add("The username length must be greater than 6");
    	}
        if(Pattern.compile(".*[$#@%^&*]+.*").matcher(username).matches()) {
            errs.add("The username does not have special characters $#@%^&*");
        }
        // validation password 
        if(password.length() <=8) {
        	errs.add("The password length must be greater than 8");
        }
        Pattern[] regx = new Pattern[3];
        regx[0] = Pattern.compile(".*[A-Z]+.*");
        regx[1] = Pattern.compile(".*[0-9]+.*");
        regx[2] = Pattern.compile(".*[$#@%^&*]+.*");
        for (Pattern pr : regx) {
            if (!pr.matcher(password).matches()) {
                errs.add("The password must contain at least One Uppercase Letter,One Digit "
                        + "and One Speciel Syntax($#@%^&*)");  
                break;
            }
        }
        return errs.isEmpty();
    }
    
    public String errorToString(){
        return String.join("<br/>", errs);
    }
}
