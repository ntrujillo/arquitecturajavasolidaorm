package com.arquitecturajava.aplicacion.jpa;

import com.arquitecturajava.aplicacion.dao.GenericDao;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by ntrujillo on 16/04/17.
 */
public class GenericDaoImpl<T, Id extends Serializable> implements GenericDao<T, Id> {

    public Class<T> claseDePersistencia;

    public GenericDaoImpl() {
        this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T buscarPorClave(Id id) {
        EntityManagerFactory factorySession = JPAHelper.getJPAFactory();
        EntityManager manager = factorySession.createEntityManager();

        try {
            return (T) manager.find(claseDePersistencia, id);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<T> buscarTodos() {
        EntityManagerFactory factorySession = JPAHelper.getJPAFactory();
        EntityManager manager = factorySession.createEntityManager();

        try {
            TypedQuery<T> query = manager.createQuery("select o from "+claseDePersistencia.getSimpleName()+" o",claseDePersistencia);
            return query.getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public void insertar(T object) {

        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = manager.getTransaction();
            tx.begin();
            manager.persist(object);
            tx.commit();
        } catch (PersistenceException e) {
            manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }

    }

    @Override
    public void salvar(T object) {

        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = manager.getTransaction();
            tx.begin();
            manager.merge(object);
            tx.commit();
        } catch (PersistenceException e) {
            manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }

    }

    @Override
    public void borrar(T object) {
        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = manager.getTransaction();
            tx.begin();
            manager.remove(manager.merge(object));
            tx.commit();
        } catch (PersistenceException e) {
            manager.getTransaction().rollback();
            throw e;
        } finally {
            manager.close();
        }

    }
}
