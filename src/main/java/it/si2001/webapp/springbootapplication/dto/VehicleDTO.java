package it.si2001.webapp.springbootapplication.dto;

import java.io.Serializable;
import java.util.Date;

public class VehicleDTO implements Serializable {

    private static final long serialVersionUID = -1269551256763341475L;
    private int id;
    private String uniqueId;
    private String manufacturer;
    private String model;
    private Date carRegistration;
    private int categoryId;
}
