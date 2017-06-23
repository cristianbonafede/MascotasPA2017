package com.mascotas.seguridad.rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mascotas.application.dtos.FormError;
import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.seguridad.UsuariosService;
import com.mascotas.seguridad.dto.RegistrarUsuarioDTO;

/**
 * Servicio RESTFUL para acceder al negocio de usuarios.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
@Path("/usuarios")
public class UsuariosControler {
	@EJB
	UsuariosService usuarioService;

	/**
	 * Registra un nuevo Usuario en el Sistema.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response registrarUsuario(RegistrarUsuarioDTO usuarioDTO) throws NamingException {
		try {
			usuarioService.registrarUsuario(usuarioDTO);
			return Response.ok().entity(usuarioService.findByLogin(usuarioDTO.getLogin())).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}

}