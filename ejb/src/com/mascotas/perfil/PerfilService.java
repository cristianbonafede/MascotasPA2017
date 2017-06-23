package com.mascotas.perfil;

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
import com.mascotas.perfil.dto.ActualizarPerfilDTO;
import com.mascotas.perfil.entities.Perfil;
import com.mascotas.perfil.repository.PerfilesRepository;
import com.mascotas.provincias.repository.ProvinciasRepository;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;

/**
 * Servicios de negocio de Perfiles de Usuario
 * 
 * @author nestor
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PerfilService {
	@EJB
	private UsuariosRepository usuarioRepository;

	@EJB
	private PerfilesRepository perfilRepository;

	@EJB
	private ProvinciasRepository provinciaRepository;

	@EJB
	private PerfilServiceValidations perfilValidations;

	/**
	 * Busca un Perfil de usuario por login de usuario.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ActualizarPerfilDTO findForLogin(String login) throws BusinessException {
		List<ValidationError> errors = perfilValidations.validarFindForLogin(login);
		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}

		Usuario usuario = usuarioRepository.get(login);
		Perfil perfil = perfilRepository.getByUsuario(usuario.getLogin());
		if (perfil == null) {
			perfil = new Perfil();
			perfil.setUsuario(usuario);
			perfilRepository.add(perfil);
		}

		return ActualizarPerfilDTO.Factory.get(perfil);
	}

	/**
	 * Actualiza los datos basicos del perfil de usuario.
	 * 
	 */
	public void actualizarPerfil(String login, ActualizarPerfilDTO perfil) throws BusinessException {
		List<ValidationError> errors = perfilValidations.validarActualizarPerfil(login, perfil);

		if (errors.size() > 0) {
			throw new BusinessException("Error al guardar el perfil.", errors);
		}

		Perfil perfilEditado = perfilRepository.getByUsuario(login);
		if (perfilEditado == null) {
			Usuario usuario = usuarioRepository.get(login);

			perfilEditado = new Perfil();
			perfilEditado.setUsuario(usuario);
			perfilRepository.add(perfilEditado);
		}

		perfilEditado.setEmail(perfil.getEmail());
		perfilEditado.setDireccion(perfil.getDireccion());
		perfilEditado.setNombre(perfil.getNombre());
		perfilEditado.setTelefono(perfil.getTelefono());

		if (perfil.getProvincia() != null && perfil.getProvincia() > 0) {
			perfilEditado.setProvincia(provinciaRepository.get(perfil.getProvincia()));
		} else {
			perfilEditado.setProvincia(null);
		}
	}

}
