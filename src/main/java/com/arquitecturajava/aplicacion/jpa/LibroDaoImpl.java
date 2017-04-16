package com.arquitecturajava.aplicacion.jpa;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.LibroDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by admuser on 16/04/17.
 */
public class LibroDaoImpl extends GenericDaoImpl<Libro, String> implements LibroDao {


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
