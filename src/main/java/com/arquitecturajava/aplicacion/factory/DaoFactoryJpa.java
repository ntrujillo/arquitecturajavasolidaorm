package com.arquitecturajava.aplicacion.factory;

import com.arquitecturajava.aplicacion.dao.CategoriaDao;
import com.arquitecturajava.aplicacion.dao.LibroDao;
import com.arquitecturajava.aplicacion.jpa.CategoriaDaoImpl;
import com.arquitecturajava.aplicacion.jpa.LibroDaoImpl;

/**
 * Created by admuser on 16/04/17.
 */
public class DaoFactoryJpa implements DaoFactory{

    @Override
    public CategoriaDao getCategoriaDao(){
        return new CategoriaDaoImpl();
    }
    @Override
    public LibroDao getLibroDao(){
        return new LibroDaoImpl();
    }
}
