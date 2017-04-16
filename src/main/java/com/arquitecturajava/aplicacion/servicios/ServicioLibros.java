package com.arquitecturajava.aplicacion.servicios;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;

import java.util.List;

/**
 * Created by admuser on 16/04/17.
 */
public interface ServicioLibros {

    void salvarLibro(Libro l);
    void borrarLibro(Libro l);
    List<Libro> buscarTodosLosLibros();
    List<Categoria> buscarTodasLasCategorias();
    Libro buscarLibroPorClave(String isbn);
    Categoria buscarCategoriaPorClave(int id);
    List<Libro> buscarLibroPorCategoria(Categoria categoria);


}
