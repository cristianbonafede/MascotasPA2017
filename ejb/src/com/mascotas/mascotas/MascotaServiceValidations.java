package com.mascotas.mascotas;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.mascotas.application.exceptions.ValidationError;
import com.mascotas.application.utils.StringUtils;
import com.mascotas.mascotas.dto.MascotaDTO;
import com.mascotas.mascotas.entities.Mascota;
import com.mascotas.mascotas.repository.MascotaRepository;
import com.mascotas.perfil.repository.PerfilesRepository;
import com.mascotas.provincias.repository.ProvinciasRepository;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;

@Stateless
@LocalBean
public class MascotaServiceValidations {
	@EJB
	private UsuariosRepository usuariosRepository;

	@EJB
	private PerfilesRepository perfilRepository;

	@EJB
	private ProvinciasRepository provinciaRepository;

	@EJB
	private MascotaRepository mascotaRepository;

	/**
	 * Valida las mascotas para un login de usuario.
	 * 
	 * @param login
	 * @return
	 */
	public List<ValidationError> validarFindForLogin(String login) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (login == null || login.isEmpty()) {
			errors.add(new ValidationError("login", "Debe definir el login de usuario."));
			return errors;
		}

		Usuario usuario = usuariosRepository.get(login);
		if (usuario == null) {
			errors.add(new ValidationError("login", "El usuario especificado no se encuentra."));
			return errors;
		}

		return errors;
	}

	/**
	 * Valida la operacion de actualizar una masctoa
	 * 
	 * @param login
	 *            Login del usuario dueño de la masctoa
	 * @param mascota
	 *            Mascota a actualizar.
	 * @return
	 */
	public List<ValidationError> validarActualizarMascota(String login, MascotaDTO mascota) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		if (login == null || login.isEmpty()) {
			errors.add(new ValidationError("login", "Debe definir el login de usuario."));
			return errors;
		}
		Usuario usuario = usuariosRepository.get(login);
		if (usuario == null) {
			errors.add(new ValidationError("login", "El usuario especificado no se encuentra."));
			return errors;
		}

		if (mascota == null) {
			errors.add(new ValidationError("perfil", "Debe definir el perfil a actualizar."));
			return errors;
		}
		
		if(mascota.getId() != null) {
			errors.addAll(validarFindById(login,mascota.getId()));
		}

		try {
			StringUtils.DATE_FORMAT.parse(mascota.getFechaNacimiento());
		} catch (ParseException e) {
			errors.add(new ValidationError("fechaNacimiento", "Debe definir la fecha de nacimiento."));
		}
		
		if(mascota.getFechaNacimiento() == null) {
			errors.add(new ValidationError("fechaNacimiento", "Debe definir la fecha de nacimiento."));
		}
		
		if (mascota.getNombre() == null || mascota.getNombre().length() < 3) {
			errors.add(new ValidationError("nombre", "Debe definir el nombre de la mascota."));
		}
		
		

		// TODO Auto-generated method stub
		return errors;
	}

	/**
	 * Valida que el usuario logueado tenga permisos para acceder a la mascota
	 * requerida
	 * 
	 * @param login
	 * @param id
	 * @return
	 */
	public List<ValidationError> validarFindById(String login, Integer id) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		Usuario usuario = usuariosRepository.get(login);
		if (usuario == null) {
			errors.add(new ValidationError("login", "Usuario no logueado."));
			return errors;
		}

		Mascota m = mascotaRepository.get(id);
		if (m == null || m.getUsuario() == null || !m.getUsuario().getLogin().equals(login)) {
			errors.add(new ValidationError("login", "La mascota requerida no pertenece al usuario logueado."));
			return errors;
		}

		return errors;

	}


	/**
	 * Valida que el usuario logueado tenga permisos para acceder a la mascota
	 * requerida
	 * 
	 * @param login
	 * @param id
	 * @return
	 */
	public List<ValidationError> validarEliminarMascota(String login, Integer id) {
		List<ValidationError> errors = new ArrayList<ValidationError>();

		Usuario usuario = usuariosRepository.get(login);
		if (usuario == null) {
			errors.add(new ValidationError("login", "Usuario no logueado."));
			return errors;
		}

		Mascota m = mascotaRepository.get(id);
		if (m == null || m.getUsuario() == null || !m.getUsuario().getLogin().equals(login)) {
			errors.add(new ValidationError("login", "La mascota requerida no pertenece al usuario logueado."));
			return errors;
		}

		return errors;

	}
}
