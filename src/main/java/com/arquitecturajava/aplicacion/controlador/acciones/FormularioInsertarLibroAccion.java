package com.arquitecturajava.aplicacion.controlador.acciones;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.dao.CategoriaDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author cecilio alvarez caules contacto@arquitecturajava.com
 * @version 1.0
 */
public class FormularioInsertarLibroAccion extends Accion {

    @Override
    public String ejecutar(HttpServletRequest request,
                           HttpServletResponse response) {
        CategoriaDao categoriaDao = new CategoriaDao();

        List<Categoria> listaDeCategorias = null;

        listaDeCategorias = categoriaDao.buscarTodos();
        request.setAttribute("listaDeCategorias", listaDeCategorias);
        return "FormularioInsertarLibro.jsp";
    }

}
