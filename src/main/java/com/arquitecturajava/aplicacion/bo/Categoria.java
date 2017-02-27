package com.arquitecturajava.aplicacion.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

import com.arquitecturajava.aplicacion.JPAHelper;

@Entity
@Table(name = "categorias")
public class Categoria {
	@Id
	private int id;
	private String descripcion;
	@OneToMany
	@JoinColumn(name = "categoria")
	private List<Libro> listaDeLibros;

	public Categoria(int id) {
		super();
		this.id = id;
	}

	public Categoria() {
		super();
	}

	public Categoria(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Libro> getListaDeLibros() {
		return listaDeLibros;
	}

	public void setListaDeLibros(List<Libro> listaDeLibros) {
		this.listaDeLibros = listaDeLibros;
	}

	public static List<Categoria> buscarTodos() {

		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();

		List<Categoria> listaDeCategorias = new ArrayList<Categoria>();
		try {

			TypedQuery<Categoria> consulta = manager.createQuery("Select c from Categoria c", Categoria.class);

			listaDeCategorias = consulta.getResultList();
			return listaDeCategorias;
		} finally {
			manager.close();
		}

	}

	public static Categoria buscarPorClave(int id) {

		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();

		TypedQuery<Categoria> consulta = manager.createQuery("Select c from Categoria c where c.id=?1",
				Categoria.class);

		consulta.setParameter(1, id);
		Categoria categoria = new Categoria();

		try {

			categoria = consulta.getSingleResult();

			return categoria;

		} finally {

			manager.close();
		}

	}

}
