package com.mascotas.concurso.repository;



import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mascotas.application.repository.Repositorio;
import com.mascotas.concurso.entities.Concurso;



@Stateless
@LocalBean
public class ConcursoRepository implements Repositorio<Integer, Concurso> {

	@PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;

	@Override
	public void add(Concurso newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Concurso toDelete) {
		throw new RuntimeException("No se puede elimiar un concurso.");
	}

	@Override
	public Concurso get(Integer id) {
		return entityManager.find(Concurso.class, id);
	}

	@Override
	public List<Concurso> getAll() {
		String q = "SELECT p from " + Concurso.class.getName() + " p ";
		TypedQuery<Concurso> query = entityManager.createQuery(q, Concurso.class);

		List<Concurso> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Concurso>();
		}
		return result;
	}
	

	@Override
	public long size() {
		throw new RuntimeException("No se puede acceder a todos los concurso.");
	}

	public Concurso getByUsuario(String login) {
		String q = "SELECT p from " + Concurso.class.getName() + " p JOIN p.usuario u WHERE  u.login = :login ";
		TypedQuery<Concurso> query = entityManager.createQuery(q, Concurso.class);
		query.setParameter("login", login);
		List<Concurso> result = query.getResultList();
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}
}
