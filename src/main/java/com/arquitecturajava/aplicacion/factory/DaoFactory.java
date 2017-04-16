package com.arquitecturajava.aplicacion.factory;

import com.arquitecturajava.aplicacion.dao.CategoriaDao;
import com.arquitecturajava.aplicacion.dao.LibroDao;

/**
 * Created by admuser on 16/04/17.
 */
public interface DaoFactory {
    CategoriaDao getCategoriaDao();

    LibroDao getLibroDao();
}
