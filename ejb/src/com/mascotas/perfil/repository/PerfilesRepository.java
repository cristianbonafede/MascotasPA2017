package com.mascotas.perfil.repository;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mascotas.application.repository.Repositorio;
import com.mascotas.perfil.entities.Perfil;

/**
 * Repositorio de Almacenamiento de Perfiles de Usuario.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
public class PerfilesRepository implements Repositorio<Integer, Perfil> {
	@PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;

	@Override
	public void add(Perfil newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Perfil toDelete) {
		throw new RuntimeException("No se puede elimiar un perfil.");
	}

	@Override
	public Perfil get(Integer id) {
		return entityManager.find(Perfil.class, id);
	}

	@Override
	public List<Perfil> getAll() {
		throw new RuntimeException("No se puede acceder a todos los perfiles.");
	}

	@Override
	public long size() {
		throw new RuntimeException("No se puede acceder a todos los perfiles.");
	}

	public Perfil getByUsuario(String login) {
		String q = "SELECT p from " + Perfil.class.getName() + " p JOIN p.usuario u WHERE  u.login = :login ";
		TypedQuery<Perfil> query = entityManager.createQuery(q, Perfil.class);
		query.setParameter("login", login);
		List<Perfil> result = query.getResultList();
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
}
