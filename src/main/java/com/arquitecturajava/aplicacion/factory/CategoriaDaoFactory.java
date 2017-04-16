package com.arquitecturajava.aplicacion.factory;

import com.arquitecturajava.aplicacion.dao.CategoriaDao;
import com.arquitecturajava.aplicacion.jpa.CategoriaDaoImpl;

/**
 * Created by admuser on 16/04/17.
 */
public class CategoriaDaoFactory {

    public static CategoriaDao getInstance() {
        return new CategoriaDaoImpl();
    }

}
