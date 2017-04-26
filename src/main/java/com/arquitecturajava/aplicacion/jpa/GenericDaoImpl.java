package com.arquitecturajava.aplicacion.jpa;

import com.arquitecturajava.aplicacion.dao.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by ntrujillo on 16/04/17.
 */
public class GenericDaoImpl<T, Id extends Serializable> implements GenericDao<T, Id> {

    private Class<T> claseDePersistencia;

    private EntityManagerFactory entityManagerFactory;

    public GenericDaoImpl() {
        this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public T buscarPorClave(Id id) {
        EntityManager manager = getEntityManagerFactory().createEntityManager();

        try {
            return (T) manager.find(claseDePersistencia, id);
        } finally {
            manager.close();
        }
    }

    @Override
    public List<T> buscarTodos() {

        EntityManager manager = getEntityManagerFactory().createEntityManager();

        try {
            TypedQuery<T> query = manager.createQuery("select o from " + claseDePersistencia.getSimpleName() + " o", claseDePersistencia);
            return query.getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public void insertar(T object) {

        EntityManager manager = getEntityManagerFactory().createEntityManager();
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

        EntityManager manager = getEntityManagerFactory().createEntityManager();
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
        EntityManager manager = getEntityManagerFactory().createEntityManager();
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
