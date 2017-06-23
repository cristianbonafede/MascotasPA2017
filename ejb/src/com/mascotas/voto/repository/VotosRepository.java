package com.mascotas.voto.repository;



import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.mascotas.application.repository.Repositorio;
import com.mascotas.voto.entities.Voto;



@Stateless
@LocalBean
public class VotosRepository implements Repositorio<Integer, Voto> {

	@PersistenceContext(unitName = "MascotasDS")
	private EntityManager entityManager;

	@Override
	public void add(Voto newOne) {
		entityManager.persist(newOne);
	}

	@Override
	public void remove(Voto toDelete) {
		entityManager.remove(toDelete);
	}

	@Override
	public Voto get(Integer id) {
		return entityManager.find(Voto.class, id);
	}

	@Override
	public List<Voto> getAll() {
	
		String q = "SELECT p from " + Voto.class.getName() + " p ";
		TypedQuery<Voto> query = entityManager.createQuery(q, Voto.class);

		List<Voto> result = query.getResultList();
		if (result == null) {
			result = new ArrayList<Voto>();
		}
		return result;
	}
	

	@Override
	public long size() {
		throw new RuntimeException("No se puede acceder a todos los votos.");
	}

	public List<Voto> getByUsuario(String login) {
		String q = "SELECT p from " + Voto.class.getName() +
				" p JOIN p.usuario u WHERE  u.login = :login ";
			TypedQuery<Voto> query = entityManager.createQuery(q, Voto.class);
		query.setParameter("login", login);
		return query.getResultList();
	}
}
