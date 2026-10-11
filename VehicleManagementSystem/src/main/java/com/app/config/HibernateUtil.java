package com.app.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.app.model.Insurance;
import com.app.model.ServiceRecord;
import com.app.model.Vehicle;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactory() {

		try {

			Configuration configuration = new Configuration();

			// Database configuration
			configuration.setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver");
			configuration.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3308/vehicleManagementsystem");
			configuration.setProperty("hibernate.connection.username","root");
			configuration.setProperty("hibernate.connection.password","root");

			// Hibernate configuration
			configuration.setProperty("hibernate.hbm2ddl.auto","update");
			configuration.setProperty("hibernate.show_sql","true");

			// Register entity class
			configuration.addAnnotatedClass(Vehicle.class);
			configuration.addAnnotatedClass(ServiceRecord.class);
			configuration.addAnnotatedClass(Insurance.class);

			// Build SessionFactory
			sessionFactory = configuration.buildSessionFactory();

		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException("Error creating SessionFactory", e);
		}

		return sessionFactory;
	}
}