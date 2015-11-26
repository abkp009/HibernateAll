package com.cts.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity(name = "CUSTOMER_DETAIL")
public class CustomerDetail {
    @Id
    @GeneratedValue
    @Column(name = "CUSTOMER_ID")
    private int customerId;
    private String doj;
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Embedded
    @AttributeOverrides({
	    @AttributeOverride(name = "firstName", column = @Column(name = "CUSTOMER_FIRST_NAME")),
	    @AttributeOverride(name = "lastName", column = @Column(name = "CUSTOMER_LAST_NAME")),
	    @AttributeOverride(name = "middleName", column = @Column(name = "CUSTOMER_MIDDLE_NAME")) })
    private Name name;
    private Name parentName;
    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    private static String description;
    @ElementCollection
    @JoinTable(name = "VEHICLE_DETAIL", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))
    @GenericGenerator(name = "hilo-gen", strategy = "hilo")
    @CollectionId(columns = { @Column(name = "VEHICLE_ID") }, generator = "hilo-gen", type = @Type(type = "long"))
    private Collection<Vehicle> vehicles = new ArrayList<Vehicle>();

    // no index in hashset so can't have primary key but arraylist can have one
    public Name getParentName() {
	return parentName;
    }

    public void setParentName(Name parentName) {
	this.parentName = parentName;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
	this.address = address;
    }

    public CustomerDetail() {
	super();
    }

    @Override
    public int hashCode() {

	return customerId;
    }

    public CustomerDetail(int customerId, String doj, Name name,
	    Address address, Date dob) {
	this.setName(name);
	this.address = address;
	this.customerId = customerId;
	this.doj = doj;
	this.dob = dob;
    }

    public int getCustomerId() {
	return customerId;
    }

    public void setCustomerId(int customerId) {
	this.customerId = customerId;
    }

    public String getDoj() {
	return doj;
    }

    public void setDoj(String doj) {
	this.doj = doj;
    }

    public Name getName() {
	return name;
    }

    public void setName(Name name) {
	this.name = name;
    }

    public Date getDob() {
	return dob;
    }

    public void setDob(Date dob) {
	this.dob = dob;
    }

    public static String getDescription() {
	return description;
    }

    public static void setDescription(String description) {
	CustomerDetail.description = description;
    }

    public Collection<Vehicle> getVehicles() {
	return vehicles;
    }

    public void setVehicles(Collection<Vehicle> vehicles) {
	this.vehicles = vehicles;
    }

}
