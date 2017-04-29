package com.arquitecturajava.aplicacion.beans;

import com.arquitecturajava.aplicacion.bo.Categoria;
import com.arquitecturajava.aplicacion.bo.Libro;
import com.arquitecturajava.aplicacion.servicios.ServicioLibros;
import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admuser on 4/29/17.
 */
@ManagedBean
@SessionScoped
public class FormularioLibroManagedBean {

    private String isbn;
    private String titulo;
    private String categoria;
    private List<SelectItem> listaDeCategorias;
    private List<Libro> listaDeLibros;

    public List<Libro> getListaDeLibros() {
        return listaDeLibros;
    }

    @PostConstruct
    public void iniciar() {
        listaDeLibros = getServicioLibros().buscarTodosLosLibros();
        List<Categoria> categorias = getServicioLibros()
                .buscarTodasLasCategorias();
        listaDeCategorias = new ArrayList<SelectItem>();
        for (Categoria categoria : categorias) {
            listaDeCategorias.add(new SelectItem(categoria.getId(), categoria.getDescripcion()));
        }
    }

    public void setListaDeLibros(List<Libro> listaDeLibros) {
        this.listaDeLibros = listaDeLibros;
    }

    public List<SelectItem> getListaDeCategorias() {
        return listaDeCategorias;
    }

    public void setListaDeCategorias(List<SelectItem> listaDeCategorias) {
        this.listaDeCategorias = listaDeCategorias;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void insertar(ActionEvent evento) {
        getServicioLibros().salvarLibro(
                new Libro(isbn, titulo, new Categoria(Integer
                        .parseInt(categoria))));
        setListaDeLibros(getServicioLibros().buscarTodosLosLibros());
        categoria = "0";
    }

    public void borrar(ActionEvent evento) {
        UIComponent componente = (UIComponent) evento.getComponent();
        String isbn = componente.getAttributes().get("isbn").toString();
        getServicioLibros().borrarLibro(new Libro(isbn));
        setListaDeLibros(getServicioLibros().buscarTodosLosLibros());
    }

    public void filtrar(ValueChangeEvent evento) {
        int idCategoria = Integer.parseInt(evento.getComponent()
                .getAttributes().get("value").toString());
        if (idCategoria != 0) {
            setListaDeLibros(getServicioLibros().buscarLibroPorCategoria(new Categoria(idCategoria)));
        } else {

            setListaDeLibros(getServicioLibros().
                    buscarTodosLosLibros());
        }
    }

    public void editar(ActionEvent evento) {
        UIComponent componente = (UIComponent) evento.getComponent();
        Libro libro = getServicioLibros().buscarLibroPorClave(
                componente.getAttributes().get("isbn").toString());
        isbn = libro.getIsbn();
        titulo = libro.getTitulo();
    }

    public void formularioInsertar(ActionEvent evento) {
        isbn = "";
        titulo = "";
    }

    public void salvar(ActionEvent evento) {
        getServicioLibros().salvarLibro(
                new Libro(isbn, titulo, new Categoria(Integer
                        .parseInt(categoria))));
        setListaDeLibros(getServicioLibros().buscarTodosLosLibros());
        categoria = "0";
    }

    public ServicioLibros getServicioLibros() {
        ApplicationContext contexto = FacesContextUtils
                .getWebApplicationContext(FacesContext.getCurrentInstance());
        return (ServicioLibros) contexto.getBean("servicioLibros");
    }
}
