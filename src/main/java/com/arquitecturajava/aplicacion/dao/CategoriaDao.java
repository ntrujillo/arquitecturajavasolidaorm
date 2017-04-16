package com.arquitecturajava.aplicacion.dao;

import com.arquitecturajava.aplicacion.bo.Categoria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by admuser on 16/04/17.
 */
public class CategoriaDao {

    public List<Categoria> buscarTodos() {

        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();
        try {
            TypedQuery<Categoria> consulta = manager.createQuery("Select c from Categoria c", Categoria.class);
            return consulta.getResultList();
        } finally {
            manager.close();
        }

    }

    public Categoria buscarPorClave(int id) {

        EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
        EntityManager manager = factoriaSession.createEntityManager();

        TypedQuery<Categoria> consulta = manager.createQuery("Select c from Categoria c where c.id=?1",
                Categoria.class);
        consulta.setParameter(1, id);
        try {

            return consulta.getSingleResult();

        } finally {

            manager.close();
        }

    }




}
