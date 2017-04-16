package com.arquitecturajava.aplicacion.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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
        try {
            tx = manager.getTransaction();
            tx.begin();
            manager.persist(this);
            tx.commit();
        } catch (PersistenceException e) {
            manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    public void borrar() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = manager.getTransaction();
            tx.begin();
            manager.remove(manager.merge(this));
            tx.commit();
        } catch (PersistenceException e) {
            manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }

    }

    public void salvar() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = manager.getTransaction();
            tx.begin();
            manager.merge(this);
            tx.commit();
        } catch (PersistenceException e) {
            manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }

    }

    public static List<Libro> buscarTodos() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {
            TypedQuery<Libro> consulta = manager.createQuery("SELECT l FROM Libro l JOIN FETCH l.categoria", Libro.class);
            return consulta.getResultList();
        } finally {
            manager.close();
        }

    }

    public static Libro buscarPorClave(String isbn) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {
            TypedQuery<Libro> consulta = manager.createQuery("Select l from Libro l JOIN FETCH l.categoria where l.isbn=?1",
                    Libro.class);
            consulta.setParameter(1, isbn);
            return consulta.getSingleResult();
        } finally {
            manager.close();
        }


    }

    public static List<Libro> buscarPorCategoria(Categoria categoria) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {
            TypedQuery<Libro> consulta = manager.createQuery("Select l from Libro l where l.categoria=?1", Libro.class);
            consulta.setParameter(1, categoria);
            return consulta.getResultList();

        } finally {
            manager.close();
        }
    }

}