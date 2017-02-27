package com.arquitecturajava.aplicacion.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import com.arquitecturajava.aplicacion.JPAHelper;

@Entity
@Table(name = "libros")
public class Libro {
	@Id
	private String isbn;
	private String titulo;
	@ManyToOne
	private Categoria categoria;

	@Override
	public int hashCode() {
		return isbn.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		String isbnLibro = ((Libro) o).getIsbn();
		return isbnLibro.equals(isbn);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Libro(String isbn) {
		super();
		this.isbn = isbn;
	}

	public Libro() {
		super();
	}

	public Libro(String isbn, String titulo, Categoria categoria) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.categoria = categoria;
	}

	public void insertar() {

		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		tx = manager.getTransaction();
		tx.begin();
		manager.persist(this);
		tx.commit();
	}

	public void borrar() {
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		tx = manager.getTransaction();
		tx.begin();
		manager.remove(manager.merge(this));
		tx.commit();
		manager.close();
	}

	public void salvar() {
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		tx = manager.getTransaction();
		tx.begin();
		manager.merge(this);
		tx.commit();
		manager.close();
	}

	public static List<Libro> buscarTodos() {
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager.createQuery("SELECT l FROM Libro l JOIN FETCH l.categoria", Libro.class);
		List<Libro> listaDeLibros = new ArrayList<Libro>();
		listaDeLibros = consulta.getResultList();
		manager.close();
		return listaDeLibros;
	}

	public static Libro buscarPorClave(String isbn) {
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager.createQuery("Select l from Libro l JOIN FETCH l.categoria where l.isbn=?1",
				Libro.class);
		consulta.setParameter(1, isbn);
		Libro libro = new Libro();
		libro = consulta.getSingleResult();
		manager.close();
		return libro;
	}

	public static List<Libro> buscarPorCategoria(Categoria categoria) {
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		TypedQuery<Libro> consulta = manager.createQuery("Select l from Libro l where l.categoria=?1", Libro.class);
		consulta.setParameter(1, categoria);
		List<Libro> listaDeLibros = new ArrayList<Libro>();
		listaDeLibros = consulta.getResultList();
		manager.close();
		return listaDeLibros;
	}

}