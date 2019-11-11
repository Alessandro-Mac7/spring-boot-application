package it.si2001.webapp.springbootapplication.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -137980106513093679L;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Date date;
    private Integer typologyId;
}
