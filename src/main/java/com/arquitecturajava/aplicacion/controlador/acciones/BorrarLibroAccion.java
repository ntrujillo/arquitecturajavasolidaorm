package com.arquitecturajava.aplicacion.controlador.acciones;

import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDao;
import com.arquitecturajava.aplicacion.dao.LibroDao;
import com.arquitecturajava.aplicacion.factory.DaoAbstractFactory;
import com.arquitecturajava.aplicacion.factory.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cecilio alvarez caules contacto@arquitecturajava.com
 * @version 1.0
 */
public class BorrarLibroAccion extends Accion {

    @Override
    public String ejecutar(HttpServletRequest request,
                           HttpServletResponse response) {
        DaoFactory factory = DaoAbstractFactory.getInstance();
        LibroDao libroDao = factory.getLibroDao();
        CategoriaDao categoriaDao = factory.getCategoriaDao();
        String isbn = request.getParameter("isbn");
        Libro libro = new Libro(isbn);
        libroDao.borrar(libro);
        return "MostrarLibros.do";
    }

}
