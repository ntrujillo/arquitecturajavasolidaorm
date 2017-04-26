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
public class FiltrarLibrosPorCategoriaAccion extends Accion {

    @Override
    public String ejecutar(HttpServletRequest request,
                           HttpServletResponse response) {
        ServicioLibros servicioLibros = (ServicioLibros) getBean("servicioLibros", request);

        List<Libro> listaDeLibros = null;
        List<Categoria> listaDeCategorias = servicioLibros.buscarTodasLasCategorias();

        if (request.getParameter("categoria") == null
                || request.getParameter("categoria").equals("seleccionar")) {

            listaDeLibros = servicioLibros.buscarTodosLosLibros();

        } else {

            Categoria categoria = servicioLibros.buscarCategoriaPorClave(Integer.parseInt(request
                    .getParameter("categoria")));
            listaDeLibros = servicioLibros.buscarLibroPorCategoria(categoria);

        }
        request.setAttribute("listaDeLibros", listaDeLibros);
        request.setAttribute("listaDeCategorias", listaDeCategorias);

        return "MostrarLibros.jsp";
    }

}
