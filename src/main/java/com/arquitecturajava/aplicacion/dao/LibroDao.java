package com.arquitecturajava.aplicacion.dao;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;

import java.util.List;

/**
 * Created by admuser on 16/04/17.
 */
public interface LibroDao extends GenericDao<Libro,String> {

    List<Libro> buscarPorCategoria(Categoria categoria);
}
