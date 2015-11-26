package com.cts.vo;

import javax.persistence.Entity;

@Entity
public class CeilingFan extends Fan {
    private String ceilingFan;

    public String getCeilingFan() {
	return ceilingFan;
    }

    public void setCeilingFan(String ceilingFan) {
	this.ceilingFan = ceilingFan;
    }
}
