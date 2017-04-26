package com.arquitecturajava.aplicacion.controlador.acciones;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;

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
        ServicioLibros servicioLibros = (ServicioLibros) getBean("servicioLibros", request);
        String isbn = request.getParameter("isbn");
        List<Categoria> listaDeCategorias = servicioLibros.buscarTodasLasCategorias();
        Libro libro = servicioLibros
                .buscarLibroPorClave(isbn);
        request.setAttribute("listaDeCategorias", listaDeCategorias);
        request.setAttribute("libro", libro);
        return "FormularioEditarLibro.jsp";
    }

}
