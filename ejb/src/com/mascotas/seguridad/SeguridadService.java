package com.mascotas.seguridad;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.seguridad.dto.UsuarioDTO;

/**
 * Servicio de seguridad.
 * 
 * @author Nestor
 *
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SeguridadService {
	@EJB
	private UsuariosService usuarioService;

	/**
	 * Realiza el login de usuario.
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UsuarioDTO login(String login) throws BusinessException {

		UsuarioDTO user = usuarioService.findByLogin(login);
		if (user == null) {
			throw new BusinessException("Usuario no encontrado.");
		}
		if (user.getInicioVigencia() != null && user.getInicioVigencia().after(new java.util.Date())) {
			throw new BusinessException("El usuario no esa vigente.");
		}
		if (user.getFinVigencia() != null && user.getFinVigencia().before(new java.util.Date())) {
			throw new BusinessException("El usuario no esa vigente.");
		}

		return user;
	}

}
