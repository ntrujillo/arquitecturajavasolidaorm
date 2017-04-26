package com.arquitecturajava.aplicacion.controlador.acciones;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cecilio alvarez caules contacto@arquitecturajava.com
 * @version 1.0
 */
public class ModificarLibroAccion extends Accion {

    @Override
    public String ejecutar(HttpServletRequest request,
                           HttpServletResponse response) {

        ServicioLibros servicioLibros = (ServicioLibros) getBean("servicioLibros", request);
        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String categoria = request.getParameter("categoria");
        Categoria objetoCategoria = new Categoria(Integer.parseInt(categoria));
        Libro libro = new Libro(isbn, titulo, objetoCategoria);
        servicioLibros.salvarLibro(libro);
        return "MostrarLibros.do";
    }

}
