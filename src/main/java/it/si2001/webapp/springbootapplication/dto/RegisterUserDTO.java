package it.si2001.webapp.springbootapplication.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RegisterUserDTO implements Serializable {

    private static final long serialVersionUID = 7750199348718199377L;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Date date;
    private boolean isAdmin;


}
