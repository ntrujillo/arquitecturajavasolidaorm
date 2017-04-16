package com.arquitecturajava.aplicacion.jpa;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.LibroDao;

import javax.persistence.*;
import java.util.List;

/**
 * Created by admuser on 16/04/17.
 */
public class LibroDaoImpl implements LibroDao {

    @Override
    public void insertar(Libro l) {

        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = manager.getTransaction();
            tx.begin();
            manager.persist(l);
            tx.commit();
        } catch (PersistenceException e) {
            manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }
    }
    @Override
    public void borrar(Libro l) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = manager.getTransaction();
            tx.begin();
            manager.remove(manager.merge(l));
            tx.commit();
        } catch (PersistenceException e) {
            manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }

    }
    @Override
    public void salvar(Libro l) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = manager.getTransaction();
            tx.begin();
            manager.merge(l);
            tx.commit();
        } catch (PersistenceException e) {
            manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }

    }
    @Override
    public List<Libro> buscarTodos() {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {
            TypedQuery<Libro> consulta = manager.createQuery("SELECT l FROM Libro l JOIN FETCH l.categoria", Libro.class);
            return consulta.getResultList();
        } finally {
            manager.close();
        }

    }
    @Override
    public Libro buscarPorClave(String isbn) {
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
    @Override
    public List<Libro> buscarPorCategoria(Categoria categoria) {
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
