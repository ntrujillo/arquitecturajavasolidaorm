package com.arquitecturajava.aplicacion.controlador.acciones;

import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;

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
        ServicioLibros servicioLibros = (ServicioLibros) getBean("servicioLibros", request);
        String isbn = request.getParameter("isbn");
        Libro libro = new Libro(isbn);
        servicioLibros.borrarLibro(libro);
        return "MostrarLibros.do";
    }

}
