package com.cts.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="ADDRESS_DETAIL")
public class Address {

    @Id
    @GeneratedValue
    @Column(name="ADDRESS_ID")
    private int addressId;
    private String street;
    private String pin;
    private String city;
    private String state;
    private String country;

    public Address(int addressId, String street, String pin,
	    String city, String state, String country) {
	super();
	this.addressId = addressId;
	
	this.street = street;
	this.pin = pin;
	this.city = city;
	this.state = state;
	this.country = country;
    }

    public Address(String street, String pin, String city,
	    String state, String country) {
	super();
	
	this.street = street;
	this.pin = pin;
	this.city = city;
	this.state = state;
	this.country = country;
    }

    public Address() {
    }

    @Override
    public String toString() {

	return street + pin + city + state + country;
    }

    public String getStreet() {
	return street;
    }

    public void setStreet(String street) {
	this.street = street;
    }

    public String getPin() {
	return pin;
    }

    public void setPin(String pin) {
	this.pin = pin;
    }

    public String getCity() {
	return city;
    }

    public void setCity(String city) {
	this.city = city;
    }

    public String getState() {
	return state;
    }

    public void setState(String state) {
	this.state = state;
    }

    public String getCountry() {
	return country;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public int getAddressId() {
	return addressId;
    }

    public void setAddressId(int addressId) {
	this.addressId = addressId;
    }

   
}