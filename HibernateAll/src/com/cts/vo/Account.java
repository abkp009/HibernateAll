package com.cts.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "ACNO")
    private int acno;
    @Column(name = "TYPE")
    private String type;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    @NotFound(action=NotFoundAction.IGNORE)
    private UserDetail user;

    public int getAcno() {
	return acno;
    }

    public void setAcno(int acno) {
	this.acno = acno;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public UserDetail getUser() {
	return user;
    }

    public void setUser(UserDetail user) {
	this.user = user;
    }
}
