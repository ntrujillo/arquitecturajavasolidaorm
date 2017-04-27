package com.arquitecturajava.aplicacion.jpa;

import com.arquitecturajava.aplicacion.dao.GenericDao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


/**
 * Created by ntrujillo on 16/04/17.
 */

public class GenericDaoImpl<T, Id extends Serializable> implements GenericDao<T, Id> {

    private Class<T> claseDePersistencia;

    @PersistenceContext
    private EntityManager manager;

    public EntityManager getManager() {
        return manager;
    }

    public void setManager(EntityManager manager) {
        this.manager = manager;
    }

    public GenericDaoImpl() {
        this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @Override
    @Transactional
    public T buscarPorClave(Id id) {
        return (T) getManager().find(claseDePersistencia, id);
    }

    @Override
    @Transactional
    public List<T> buscarTodos() {
        TypedQuery<T> consulta = getManager().createQuery("select o from "
                        + claseDePersistencia.getSimpleName() + " o",
                claseDePersistencia);
        return consulta.getResultList();
    }

    @Override
    @Transactional
    public void insertar(T object) {
        getManager().persist(object);
    }

    @Override
    @Transactional
    public void salvar(T object) {
        getManager().merge(object);
    }

    @Override
    @Transactional
    public void borrar(T object) {
        getManager().remove(getManager().merge(object));
    }
}
