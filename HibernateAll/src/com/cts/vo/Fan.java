package com.cts.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedNativeQuery;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// optional if single, default single
//@NamedNativeQuery(name = "Fan.byModel", query = "{ CALL getFan(?,?) }", resultClass=Fan.class)
public class Fan {
    @Id
    @GeneratedValue
    private int modelNumber;
    private String manufacturer;

    public String getManufacturer() {
	return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
    }

    public void setModelNumber(int modelNumber) {
	this.modelNumber = modelNumber;
    }

    public int getModelNumber() {
	return modelNumber;
    }

}
