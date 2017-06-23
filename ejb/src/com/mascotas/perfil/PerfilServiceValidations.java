package com.mascotas.perfil;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.mascotas.application.exceptions.ValidationError;
import com.mascotas.perfil.dto.ActualizarPerfilDTO;
import com.mascotas.perfil.repository.PerfilesRepository;
import com.mascotas.provincias.entites.Provincia;
import com.mascotas.provincias.repository.ProvinciasRepository;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;

@Stateless
@LocalBean
public class PerfilServiceValidations {
	@EJB
	private UsuariosRepository usuariosRepository;

	@EJB
	private PerfilesRepository perfilRepository;

	@EJB
	private ProvinciasRepository provinciaRepository;

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
	 * Valida la operacion de actualizar perfil
	 * 
	 * @param login
	 *            Login del usuario dueño del perfil
	 * @param perfil
	 *            Perfil a actualizar.
	 * @return
	 */
	public List<ValidationError> validarActualizarPerfil(String login, ActualizarPerfilDTO perfil) {

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

		if (perfil == null) {
			errors.add(new ValidationError("perfil", "Debe definir el perfil a actualizar."));
			return errors;
		}

		if (perfil.getProvincia() != null && perfil.getProvincia() > 0) {
			Provincia provincia = provinciaRepository.get(perfil.getProvincia());
			if (provincia == null) {
				errors.add(new ValidationError("provincia", "La provincia proporcionada es incorrecta."));
				return errors;
			}
		}

		if (perfil.getNombre().isEmpty()) {
			errors.add(new ValidationError("nombre", "Tu nombre es requerido."));
		}

		// TODO Auto-generated method stub
		return errors;
	}

}
