package com.arquitecturajava.aplicacion.controlador.acciones;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.dao.CategoriaDao;
import com.arquitecturajava.aplicacion.dao.LibroDao;
import com.arquitecturajava.aplicacion.factory.CategoriaDaoFactory;
import com.arquitecturajava.aplicacion.factory.LibroDaoFactory;
import com.arquitecturajava.aplicacion.jpa.CategoriaDaoImpl;
import com.arquitecturajava.aplicacion.jpa.LibroDaoImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author cecilio alvarez caules contacto@arquitecturajava.com
 * @version 1.0
 */
public class FormularioEditarLibroAccion extends Accion {

    @Override
    public String ejecutar(HttpServletRequest request,
                           HttpServletResponse response) {
        LibroDao libroDao = LibroDaoFactory.getInstance();
        CategoriaDao categoriaDao = CategoriaDaoFactory.getInstance();
        String isbn = request.getParameter("isbn");
        List<Categoria> listaDeCategorias = categoriaDao.buscarTodos();
        Libro libro = libroDao
                .buscarPorClave(isbn);
        request.setAttribute("listaDeCategorias", listaDeCategorias);
        request.setAttribute("libro", libro);
        return "FormularioEditarLibro.jsp";
    }

}
