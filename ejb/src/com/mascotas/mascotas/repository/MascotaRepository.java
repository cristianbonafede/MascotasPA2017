package com.mascotas.mascotas.repository;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mascotas.application.repository.Repositorio;
import com.mascotas.mascotas.entities.Mascota;


/**
 * Repositorio de Almacenamiento de Mascotas del Usuario.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
public class MascotaRepository implements Repositorio<Integer, Mascota> {
	@PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;

	@Override
	public void add(Mascota newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Mascota toDelete) {
		entityManager.remove(toDelete);
	}

	@Override
	public Mascota get(Integer id) {
		return entityManager.find(Mascota.class, id);
	}

	@Override
	public List<Mascota> getAll() {
		throw new RuntimeException("No se puede acceder a todos los perfiles.");
	}

	@Override
	public long size() {
		throw new RuntimeException("No se puede acceder a todos los perfiles.");
	}

	public List<Mascota> getByUsuario(String login) {
		String q = "SELECT p from " + Mascota.class.getName() +
				" p JOIN p.usuario u WHERE  u.login = :login ";
	
		TypedQuery<Mascota> query = entityManager.createQuery(q, Mascota.class);
		query.setParameter("login", login);
		return query.getResultList();
	}
}
