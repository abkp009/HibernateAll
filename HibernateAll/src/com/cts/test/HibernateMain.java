package com.cts.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.metamodel.Type;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;

import com.cts.vo.Account;
import com.cts.vo.Address;
import com.cts.vo.CeilingFan;
import com.cts.vo.CustomerDetail;
import com.cts.vo.Fan;
import com.cts.vo.Name;
import com.cts.vo.TableFan;
import com.cts.vo.UserDetail;
import com.cts.vo.Vehicle;

public class HibernateMain {

    public static void main(String[] args) {

	// processCustomerDetails();
	// processUserDetails();
	// processFan();
	// hql();
	namedNativeQuery();

    }

    @SuppressWarnings("unchecked")
    // not working
    private static void namedNativeQuery() {

	Session session = null;

//	List<Fan> fans = null;
//	try {
//	    session = getSessionFactory().openSession();
//	    org.hibernate.Query query = session.getNamedQuery("Fan.byModel");
//	    //query.setParameter(arg0, arg1, arg2)
//	    query.setParameter(0, EnumType.//);
//	    query.setInteger(1, 2);
//	    fans = query.list();
//	} catch (Exception e) {
//	    System.out.println(e.getMessage());
//	} finally {
//	    session.close();
//	}
//	System.out.println("ModelNumber :   Manufacturer");
//	for (Fan fan : fans) {
//	    System.out.println(fan.getModelNumber() + "\t :       "
//		    + fan.getManufacturer());
//	}

	session = getSessionFactory().openSession();
	session.doWork(new Work() {

	    @Override
	    public void execute(Connection conn) throws SQLException {
		CallableStatement stmt = conn
			.prepareCall("{ call getFan(?,?) }");
		stmt.registerOutParameter(1, OracleTypes.CURSOR);
		stmt.setInt(2, 1);
		int i = stmt.executeUpdate();
		System.out.println(i);
		ResultSet rs = (ResultSet) stmt.getObject(1);
		while (rs.next()) {
		    System.out.println(rs.getString("manufacturer"));
		}
		rs.close();
		stmt.close();

	    }

	});
	session.close();
    }

    @SuppressWarnings("unchecked")
    private static void hql() {

	Session session = null;
	List<Fan> fans = null;
	try {
	    session = getSessionFactory().openSession();
	    String hqlQuery = "From Fan";
	    String hqlQuery2 = "select manufacturer From Fan";
	    String hqlQuery3 = "select new map(model,manufacturer) From Fan";
	    String hqlQuery4 = "select max(model) From Fan";
	    org.hibernate.Query query = session.createQuery(hqlQuery);
	    query.setFirstResult(2).setMaxResults(4);
	    fans = query.list();

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	} finally {
	    session.close();
	}
	System.out.println("ModelNumber :   Manufacturer");
	for (Fan fan : fans) {
	    System.out.println(fan.getModelNumber() + "\t :       "
		    + fan.getManufacturer());
	}

    }

    private static void processFan() {

	Fan fan = new Fan();
	fan.setManufacturer("Usha");

	TableFan tableFan = new TableFan();
	tableFan.setTableFan("table fan");
	tableFan.setManufacturer("PSPO");

	CeilingFan ceilingFan = new CeilingFan();
	ceilingFan.setCeilingFan("ceilingFan");
	ceilingFan.setManufacturer("ROXY");

	Fan f = new TableFan();
	f.setManufacturer("orient");

	Session session = null;
	try {
	    session = getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    session.save(fan);
	    session.save(tableFan);
	    session.save(ceilingFan);
	    session.save(f);
	    tx.commit();

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	} finally {
	    session.close();
	}

    }

    // oneToMany
    private static void processUserDetails() {
	UserDetail user = new UserDetail();
	user.setName("Abhishek Pandey");
	Account acc = new Account();
	acc.setType("Saving");
	user.getAccounts().add(acc);
	acc.setUser(user);
	Session session = null;
	try {
	    session = getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    session.save(user);
	    // session.save(acc); cascadeType.ALL/Persist
	    tx.commit();
	    session.close();

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	} finally {
	    session.close();
	}

    }

    private static void processCustomerDetails() {
	Session session = null;

	Address address = populateAddress();
	Name name = populateName();
	Name parentName = populateParentName();
	Collection<Vehicle> list = populateVehicles();
	CustomerDetail customer = populateCustomer(address, name, list,
		parentName);
	try {
	    session = getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    session.save(customer);
	    session.save(address);
	    tx.commit();

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	} finally {
	    session.close();
	}

    }

    private static Name populateParentName() {
	return new Name("Chandra", "Pandey", "Shekhar");

    }

    private static Collection<Vehicle> populateVehicles() {
	Collection<Vehicle> list = new HashSet<Vehicle>();
	Vehicle veh = new Vehicle();
	veh.setRegistrationNumber("JH01AD1239");
	veh.setVehicleType("2");
	list.add(veh);

	Vehicle veh2 = new Vehicle();
	veh2.setRegistrationNumber("XYZ1234");
	veh2.setVehicleType("4");
	list.add(veh2);
	return list;
    }

    private static Name populateName() {
	Name name = new Name();
	name.setFirstName("Abhishek");
	name.setLastName("Pandey");
	name.setMiddleName("Kumar");
	return name;
    }

    private static Address populateAddress() {
	return new Address("MIG7 3A1 Greenwood Park, New Town, Near Dlf1",
		"700156", "Kolkata", "WB", "India");

    }

    @SuppressWarnings("deprecation")
    private static CustomerDetail populateCustomer(Address address, Name name,
	    Collection<Vehicle> list, Name parentName) {
	CustomerDetail customer = new CustomerDetail();
	customer.setAddress(address);
	customer.setCustomerId(100);
	customer.setDoj("10-Nov-2015");
	customer.setDob(new Date(1988, 0, 07));
	customer.setParentName(parentName);
	customer.getVehicles().addAll(list);
	CustomerDetail.setDescription("Customer Details");// static not saved in
							  // DB
	customer.setName(name);
	return customer;

    }

    private static SessionFactory getSessionFactory() {
	// Create configuration instance
	Configuration configuration = new Configuration();

	// Pass hibernate configuration file
	configuration.configure("hibernate.cfg.xml");

	// Since version 4.x, service registry is being used
	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();

	// Create session factory instance
	return configuration.buildSessionFactory(serviceRegistry);
    }

}
