package com.mascotas.concurso;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.application.exceptions.ValidationError;
import com.mascotas.concurso.dto.ActualizarConcursoDTO;
import com.mascotas.concurso.dto.ConcursoDTO;
import com.mascotas.concurso.entities.Concurso;
import com.mascotas.concurso.repository.ConcursoRepository;
import com.mascotas.mascotas.repository.MascotaRepository;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;


@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ConcursoService {
		@EJB
		private UsuariosRepository usuarioRepository;

		@EJB
		private ConcursoRepository concursoRepository;
 
		@EJB
		private MascotaRepository mascotaRepository;

		@EJB
		private ConcursoServiceValidations concursoValidations;

//Busca un concuros por usuario
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public ActualizarConcursoDTO findForLogin(String login) throws BusinessException {
			List<ValidationError> errors = concursoValidations.validarFindForLogin(login);
			if (errors.size() > 0) {
				throw new BusinessException("Datos de usuario invalidos.", errors);
			}

			Usuario usuario = usuarioRepository.get(login);
			Concurso concurso = concursoRepository.getByUsuario(usuario.getLogin());
			if (concurso == null) {
				concurso = new Concurso();
				concurso.setUsuario(usuario);
				concursoRepository.add(concurso);
			}

			return ActualizarConcursoDTO.Factory.get(concurso);
		}
		
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<ConcursoDTO> findAll() {
			return ConcursoDTO.Factory.get(concursoRepository.getAll());
		}

//Actualiza Concurso
		public void actualizarConcurso(String login, ActualizarConcursoDTO concurso) throws BusinessException {
			List<ValidationError> errors = concursoValidations.validarActualizarConcurso(login, concurso);

			if (errors.size() > 0) {
				throw new BusinessException("Error al guardar el concurso.", errors);
			}

			Concurso concursoEditado = concursoRepository.getByUsuario(login);
			if (concursoEditado == null) {
				Usuario usuario = usuarioRepository.get(login);

				concursoEditado = new Concurso();
				concursoEditado.setUsuario(usuario);
				concursoRepository.add(concursoEditado);
			}

			concursoEditado.setDescripcion(concurso.getDescripcion());

			if (concurso.getMascota() != null && concurso.getMascota() > 0) {
				concursoEditado.setMascota(mascotaRepository.get(concurso.getMascota()));
			} else {
				concursoEditado.setMascota(null);
			}
		}

	
}
