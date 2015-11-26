package com.cts.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Vehicle {

    private String registrationNumber;
    private String vehicleType;

    public String getRegistrationNumber() {
	return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
	this.registrationNumber = registrationNumber;
    }

    public String getVehicleType() {
	return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
    }
}
