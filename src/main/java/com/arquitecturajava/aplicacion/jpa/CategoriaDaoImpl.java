package com.arquitecturajava.aplicacion.jpa;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.dao.CategoriaDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by admuser on 16/04/17.
 */
@Repository
public class CategoriaDaoImpl extends GenericDaoImpl<Categoria, Integer> implements CategoriaDao {


}
