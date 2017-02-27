package com.arquitecturajava.aplicacion.controlador.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;


/**
 * @author      cecilio alvarez caules contacto@arquitecturajava.com
 * @version     1.0                        
 */
public class InsertarLibroAccion extends Accion{

	@Override
	public String ejecutar(HttpServletRequest request,
			HttpServletResponse response) {

		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		String categoria = request.getParameter("categoria");	
		Categoria objetoCategoria= new Categoria(Integer.parseInt(categoria));
		
		Libro libro = new Libro(isbn, titulo, objetoCategoria);
		libro.insertar();
		
		
		return "MostrarLibros.do";
	}

	
	
	
}
