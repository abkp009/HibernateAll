package com.cts.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Name {

    private String firstName;

    private String lastName;

    private String middleName;

    public Name(String firstName, String lastName, String middleName) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.middleName = middleName;
    }

    public Name() {
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getMiddleName() {
	return middleName;
    }

    public void setMiddleName(String middleName) {
	this.middleName = middleName;
    }
}
