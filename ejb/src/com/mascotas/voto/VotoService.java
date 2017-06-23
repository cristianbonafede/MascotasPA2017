package com.mascotas.voto;

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
import com.mascotas.concurso.repository.ConcursoRepository;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;
import com.mascotas.voto.dto.ActualizarVotoDTO;
import com.mascotas.voto.dto.VotoDTO;
import com.mascotas.voto.entities.Voto;
import com.mascotas.voto.repository.VotosRepository;


	
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class VotoService {
		@EJB
		private UsuariosRepository usuarioRepository;

		@EJB
		private VotosRepository votosRepository;
 
		@EJB
		private ConcursoRepository concursoRepository;

		@EJB
		private VotoServiceValidations votoValidations;



		//Buscar voto del usuario logueado
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<VotoDTO> findForLogin(String login) throws BusinessException {
			List<ValidationError> errors = votoValidations.validarFindForLogin(login);
			if (errors.size() > 0) {
				throw new BusinessException("Datos de usuario invalidos.", errors);
			}

			Usuario usuario = usuarioRepository.get(login);
			List<Voto> votos = votosRepository.getByUsuario(usuario.getLogin());

			return VotoDTO.Factory.get(votos);
		}
		
		
		@TransactionAttribute(TransactionAttributeType.REQUIRED)
		public List<VotoDTO> findAll() {
			return VotoDTO.Factory.get(votosRepository.getAll());
		}


		//Actualiza el voto
		public void actualizarVoto(String login, ActualizarVotoDTO voto) throws BusinessException {
		
			List<ValidationError> errors = votoValidations.validarActualizarVoto(login, voto);
	
			if (errors.size() > 0) {
				throw new BusinessException("Error al guardar el VOTO.", errors);
			}
			Voto votoEditado = null;
			if (voto.getId() != null) {
				votoEditado = votosRepository.get(voto.getId());
				
			}


			if (votoEditado == null) {
				Usuario usuario = usuarioRepository.get(login);

				votoEditado = new Voto();
				votoEditado.setUsuario(usuario);
				votosRepository.add(votoEditado);
			}

			if (voto.getConcurso() != null && voto.getConcurso() > 0) {

				votoEditado.setConcurso(concursoRepository.get(voto.getConcurso()));
			} else {
				votoEditado.setConcurso(null);
			}
		}

		public void eliminarVoto(String name, Integer id) {
			List<ValidationError> errors = votoValidations.validarEliminarVoto(name, id);
			if (errors.size() > 0) {
				throw new BusinessException("Error al eliminar el Voto.", errors);
			}
			votosRepository.remove(votosRepository.get(id));
		}
}
