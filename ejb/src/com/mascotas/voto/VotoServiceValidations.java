package com.mascotas.voto;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.mascotas.application.exceptions.ValidationError;
import com.mascotas.concurso.repository.ConcursoRepository;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;
import com.mascotas.voto.dto.ActualizarVotoDTO;
import com.mascotas.voto.entities.Voto;
import com.mascotas.voto.repository.VotosRepository;

@Stateless
@LocalBean
public class VotoServiceValidations {




		@EJB
		private UsuariosRepository usuariosRepository;

		@EJB
		private VotosRepository votosRepository;

		@EJB
		private ConcursoRepository concursoRepository;

		
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


		//VAlidar actulizar Voto
		public List<ValidationError> validarActualizarVoto(String login, ActualizarVotoDTO voto) {

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

			if (voto == null) {
				
				errors.add(new ValidationError("voto", "Debe definir el voto a actualizar."));
				return errors;
			}
			
			
			List<Voto> votos = votosRepository.getByUsuario(usuario.getLogin());
			if(votos != null){
			for (Voto voto2 : votos) {
				if (voto2.getConcurso().getId()== voto.getConcurso()) {
					errors.add(new ValidationError("voto", "Ya ha votado esa mascota"));
					return errors;	
				}

			}
			}

			return errors;
		}
		public List<ValidationError> validarEliminarVoto(String login, Integer id) {
			List<ValidationError> errors = new ArrayList<ValidationError>();

			Usuario usuario = usuariosRepository.get(login);
			if (usuario == null) {
				errors.add(new ValidationError("login", "Usuario no logueado."));
				return errors;
			}

			Voto m = votosRepository.get(id);
			if (m == null || m.getUsuario() == null || !m.getUsuario().getLogin().equals(login)) {
				errors.add(new ValidationError("login", "El voto requerida no pertenece al usuario logueado."));
				return errors;
			}

			return errors;

		}
	

}
