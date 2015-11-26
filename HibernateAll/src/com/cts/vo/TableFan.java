package com.cts.vo;

import javax.persistence.Entity;

@Entity
public class TableFan extends Fan {
   private String tableFan;

public String getTableFan() {
    return tableFan;
}

public void setTableFan(String tableFan) {
    this.tableFan = tableFan;
}
}
