package com.arquitecturajava.aplicacion.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ntrujillo on 16/04/17.
 */
public interface GenericDao<T, Id extends Serializable> {

    T buscarPorClave(Id id);

    List<T> buscarTodos();

    void insertar(T object);

    void salvar(T object);

    void borrar(T object);
}
