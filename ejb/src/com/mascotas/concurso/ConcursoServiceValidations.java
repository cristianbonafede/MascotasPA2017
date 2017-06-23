package com.mascotas.concurso;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.mascotas.application.exceptions.ValidationError;
import com.mascotas.concurso.dto.ActualizarConcursoDTO;
import com.mascotas.concurso.repository.ConcursoRepository;
import com.mascotas.mascotas.entities.Mascota;
import com.mascotas.mascotas.repository.MascotaRepository;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;

@Stateless
@LocalBean
public class ConcursoServiceValidations {




		@EJB
		private UsuariosRepository usuariosRepository;

		@EJB
		private ConcursoRepository concursoRepository;

		@EJB
		private MascotaRepository mascotaRepository;

		
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

//Valida el actualizar concurso
		public List<ValidationError> validarActualizarConcurso(String login, ActualizarConcursoDTO concurso) {

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

			if (concurso == null) {
				errors.add(new ValidationError("concurso", "Debe definir el concurso a actualizar."));
				return errors;
			}

			if (concurso.getMascota() != null && concurso.getMascota() > 0) {
				Mascota mascota = mascotaRepository.get(concurso.getMascota());
				if (mascota == null) {
					errors.add(new ValidationError("mascota", "La mascotaproporcionada es incorrecta."));
					return errors;
				}
			}

			if (concurso.getDescripcion().isEmpty()) {
				errors.add(new ValidationError("descripcion", "La descripcion es requerido."));
			}

			// TODO Auto-generated method stub
			return errors;
		}

	

}
