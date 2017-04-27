package com.arquitecturajava.aplicacion.servicios.impl;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDao;
import com.arquitecturajava.aplicacion.dao.LibroDao;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admuser on 16/04/17.
 */
@Service(value="servicioLibros")
public class ServicioLibrosImpl implements ServicioLibros {

    private LibroDao libroDao;
    private CategoriaDao categoriaDao;

    @Autowired
    public void setLibroDao(LibroDao libroDao) {
        this.libroDao = libroDao;
    }

    @Autowired
    public void setCategoriaDao(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

    @Override
    @Transactional
    public void salvarLibro(Libro l) {
        this.libroDao.salvar(l);
    }

    @Override
    @Transactional
    public void borrarLibro(Libro l) {
        this.libroDao.borrar(l);
    }

    @Override
    @Transactional
    public List<Libro> buscarTodosLosLibros() {
        return this.libroDao.buscarTodos();
    }

    @Override
    @Transactional
    public List<Categoria> buscarTodasLasCategorias() {
        return this.categoriaDao.buscarTodos();
    }

    @Override
    @Transactional
    public Libro buscarLibroPorClave(String isbn) {
        return this.libroDao.buscarPorClave(isbn);
    }

    @Override
    @Transactional
    public Categoria buscarCategoriaPorClave(int id) {
        return this.categoriaDao.buscarPorClave(id);
    }

    @Override
    @Transactional
    public List<Libro> buscarLibroPorCategoria(Categoria categoria) {
        return this.libroDao.buscarPorCategoria(categoria);
    }


}
