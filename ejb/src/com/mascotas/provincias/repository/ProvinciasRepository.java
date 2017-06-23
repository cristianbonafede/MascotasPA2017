package com.mascotas.provincias.repository;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mascotas.application.repository.Repositorio;
import com.mascotas.provincias.entites.Provincia;

/**
 * Repositorio de Almacenamiento de Usuarios del Sistema.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
public class ProvinciasRepository implements Repositorio<Integer, Provincia> {
	@PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;

	@Override
	public void add(Provincia newOne) {
		throw new RuntimeException("No se puede agregar provincias.");
	}

	@Override
	public void remove(Provincia toDelete) {
		throw new RuntimeException("No se puede eliminar provincias.");
	}

	@Override
	public Provincia get(Integer id) {
		return entityManager.find(Provincia.class, id);
	}

	@Override
	public List<Provincia> getAll() {
		System.out.println("Esta buscando todas las provincias");
		String q = "SELECT p from " + Provincia.class.getName() + " p ";
		TypedQuery<Provincia> query = entityManager.createQuery(q, Provincia.class);

		List<Provincia> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Provincia>();
		}
		return result;
	}

	@Override
	public long size() {
		String q = "SELECT count(p) from " + Provincia.class.getName() + " p";
		return (Long) entityManager.createQuery(q).getSingleResult();
	}
}
