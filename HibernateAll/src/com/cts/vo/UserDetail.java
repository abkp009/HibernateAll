package com.cts.vo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER_DETAIL")
public class UserDetail {

    @Id
    @GeneratedValue
    @Column(name="USER_ID")
    private int userId;
    @Column(name="NAME")
    private String name;
    @OneToMany(cascade=CascadeType.ALL,mappedBy="user",fetch=FetchType.LAZY)// operates on dependents too
    //@JoinTable(joinColumns=@JoinColumn(name="USER_ID"),inverseJoinColumns=@JoinColumn(name="ANCO"))
    private Collection<Account> accounts = new ArrayList<Account>();
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Collection<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }
   
}
