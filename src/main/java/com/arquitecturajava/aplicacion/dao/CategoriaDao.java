package com.arquitecturajava.aplicacion.dao;

import com.arquitecturajava.aplicacion.bo.Categoria;

import java.util.List;

/**
 * Created by admuser on 16/04/17.
 */
public interface CategoriaDao {

    List<Categoria> buscarTodos();

    Categoria buscarPorClave(int id);

}
