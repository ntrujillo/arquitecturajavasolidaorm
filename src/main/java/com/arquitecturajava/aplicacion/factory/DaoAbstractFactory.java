package com.arquitecturajava.aplicacion.factory;

/**
 * Created by admuser on 16/04/17.
 */
public class DaoAbstractFactory {

    public static DaoFactory getInstance(){
        return new DaoFactoryJpa();
    }
}
