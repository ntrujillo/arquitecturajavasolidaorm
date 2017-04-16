package com.arquitecturajava.aplicacion.factory;

import com.arquitecturajava.aplicacion.dao.LibroDao;
import com.arquitecturajava.aplicacion.jpa.LibroDaoImpl;

/**
 * Created by admuser on 16/04/17.
 */
public class LibroDaoFactory {

    public static LibroDao getInstance() {
        return new LibroDaoImpl();
    }
}
