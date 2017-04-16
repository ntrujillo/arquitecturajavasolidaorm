package com.arquitecturajava.aplicacion.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHelper {
	private static final EntityManagerFactory emf = buildEntityManagerFactory();

	private static EntityManagerFactory buildEntityManagerFactory() {
		try {
			return Persistence.createEntityManagerFactory("arquitecturaJava");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new RuntimeException("Error al crear la factoria de JPA");
		}
	}

	public static EntityManagerFactory getJPAFactory() {
		return emf;
	}
}