package com.arquitecturajava.aplicacion.controlador.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDao;
import com.arquitecturajava.aplicacion.dao.LibroDao;
import com.arquitecturajava.aplicacion.factory.CategoriaDaoFactory;
import com.arquitecturajava.aplicacion.factory.LibroDaoFactory;
import com.arquitecturajava.aplicacion.jpa.CategoriaDaoImpl;
import com.arquitecturajava.aplicacion.jpa.LibroDaoImpl;

/**
 * @author cecilio alvarez caules contacto@arquitecturajava.com
 * @version 1.0
 */
public class BorrarLibroAccion extends Accion {

    @Override
    public String ejecutar(HttpServletRequest request,
                           HttpServletResponse response) {
        LibroDao libroDao = LibroDaoFactory.getInstance();
        CategoriaDao categoriaDao = CategoriaDaoFactory.getInstance();

        String isbn = request.getParameter("isbn");
        Libro libro = new Libro(isbn);
        libroDao.borrar(libro);
        return "MostrarLibros.do";
    }

}
