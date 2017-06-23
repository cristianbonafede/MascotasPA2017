package com.mascotas.seguridad;

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
import com.mascotas.seguridad.dto.ActualizarUsuarioDTO;
import com.mascotas.seguridad.dto.RegistrarUsuarioDTO;
import com.mascotas.seguridad.dto.RolSeguridad;
import com.mascotas.seguridad.dto.UsuarioDTO;
import com.mascotas.seguridad.entities.Usuario;
import com.mascotas.seguridad.repository.UsuariosRepository;

/**
 * Servicio de manejo de usuarios.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuariosService {
	@EJB
	private UsuariosRepository usuariosRepository;

	@EJB
	private UsuariosServiceValidations seguridadServiceValidations;

	/**
	 * Busca un usuario por login.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UsuarioDTO findByLogin(String login) {
		return UsuarioDTO.Factory.get(usuariosRepository.get(login));
	}

	/**
	 * Busca un usuario por login.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ActualizarUsuarioDTO findForEditByLogin(String login) {
		return ActualizarUsuarioDTO.Factory.get(usuariosRepository.get(login));
	}

	/**
	 * Actualiza los datos basicos de un usuario.
	 * 
	 */
	public void actualizarUsuario(ActualizarUsuarioDTO usuario) throws BusinessException {
		List<ValidationError> errors = seguridadServiceValidations.validarActualizarUsuario(usuario);
		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}

		Usuario usuarioEditado = usuariosRepository.get(usuario.getLogin());
		for (RolSeguridad r : usuarioEditado.getRoles()) {
			usuarioEditado.removeRol(r);
		}
		for (String r : usuario.getRoles()) {
			usuarioEditado.addRol(RolSeguridad.valueOf(r));
		}
		usuarioEditado.setEmail(usuario.getEmail());
		usuarioEditado.setInicioVigencia(usuario.getInicioVigencia());
		usuarioEditado.setNombre(usuario.getNombre());

		seguridadServiceValidations.validarEntityBeanUsuario(usuarioEditado);
	}

	/**
	 * Registra un usuario en la base de datos.
	 * 
	 */
	public void registrarUsuario(RegistrarUsuarioDTO usuario) throws BusinessException {
		List<ValidationError> errors = seguridadServiceValidations.validarRegistrarUsuario(usuario);
		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}

		Usuario usuarioNuevo = new Usuario();
		usuarioNuevo.setLogin(usuario.getLogin());
		usuarioNuevo.setPlainTextPassword(usuario.getLogin(), usuario.getPassword());
		usuarioNuevo.setEmail(usuario.getEmail());
		usuarioNuevo.setInicioVigencia(new java.util.Date());
		usuarioNuevo.setNombre(usuario.getNombre());
		usuarioNuevo.addRol(RolSeguridad.USER);

		seguridadServiceValidations.validarEntityBeanUsuario(usuarioNuevo);

		usuariosRepository.add(usuarioNuevo);
	}

	/**
	 * Actgualiza la contrase�a
	 * 
	 */
	public void actualizarPassword(String login, String passwAnterior, String passwNuevo) throws BusinessException {
		List<ValidationError> errors = seguridadServiceValidations.validarCambioPassword(login, passwAnterior, passwNuevo);
		if (errors.size() > 0) {
			throw new BusinessException("Datos de usuario invalidos.", errors);
		}

		Usuario usr = usuariosRepository.get(login);
		usr.setPlainTextPassword(login, passwNuevo);
	}

	/**
	 * Activa el usuario.
	 * 
	 */
	public void activarUsuario(String login) throws BusinessException {
		List<ValidationError> errors = seguridadServiceValidations.validarActivarUsuario(login);
		if (errors.size() > 0) {
			throw new BusinessException("No se puede activar el usuario.", errors);
		}

		Usuario usr = usuariosRepository.get(login);
		usr.setFinVigencia(null);
	}

	/**
	 * Desactiva el usuario.
	 * 
	 */
	public void desactivarUsuario(String login) throws BusinessException {
		List<ValidationError> errors = seguridadServiceValidations.validarDesactivarUsuario(login);
		if (errors.size() > 0) {
			throw new BusinessException("No se puede desacivar el usuario.", errors);
		}

		Usuario usr = usuariosRepository.get(login);
		usr.setFinVigencia(new java.util.Date());
	}
}
