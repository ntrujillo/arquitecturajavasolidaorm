package com.arquitecturajava.aplicacion.bo;

import javax.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    private String isbn;
    private String titulo;
    @ManyToOne
    private Categoria categoria;

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        String isbnLibro = ((Libro) o).getIsbn();
        return isbnLibro.equals(isbn);
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Libro(String isbn) {
        super();
        this.isbn = isbn;
    }

    public Libro() {
        super();
    }

    public Libro(String isbn, String titulo, Categoria categoria) {
        super();
        this.isbn = isbn;
        this.titulo = titulo;
        this.categoria = categoria;
    }

}