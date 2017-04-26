package com.arquitecturajava.aplicacion.servicios.impl;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDao;
import com.arquitecturajava.aplicacion.dao.LibroDao;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by admuser on 16/04/17.
 */
public class ServicioLibrosImpl implements ServicioLibros {

    LibroDao libroDao;
    CategoriaDao categoriaDao;


    @Override
    public void salvarLibro(Libro l) {
        this.libroDao.salvar(l);
    }

    @Override
    public void borrarLibro(Libro l) {
        this.libroDao.borrar(l);
    }

    @Override
    public List<Libro> buscarTodosLosLibros() {
        return this.libroDao.buscarTodos();
    }

    @Override
    public List<Categoria> buscarTodasLasCategorias() {
        return this.categoriaDao.buscarTodos();
    }

    @Override
    public Libro buscarLibroPorClave(String isbn) {
        return this.libroDao.buscarPorClave(isbn);
    }

    @Override
    public Categoria buscarCategoriaPorClave(int id) {
        return this.categoriaDao.buscarPorClave(id);
    }

    @Override
    public List<Libro> buscarLibroPorCategoria(Categoria categoria) {
        return this.libroDao.buscarPorCategoria(categoria);
    }

    public void setLibroDao(LibroDao libroDao) {
        this.libroDao = libroDao;
    }

    public void setCategoriaDao(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }
}
