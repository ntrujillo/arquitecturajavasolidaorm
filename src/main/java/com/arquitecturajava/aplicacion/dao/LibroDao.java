package com.arquitecturajava.aplicacion.dao;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;

import java.util.List;

/**
 * Created by admuser on 16/04/17.
 */
public interface LibroDao {

    void insertar(Libro l);

    void borrar(Libro l);

    void salvar(Libro l);

    List<Libro> buscarTodos();

    Libro buscarPorClave(String isbn);

    List<Libro> buscarPorCategoria(Categoria categoria);
}
