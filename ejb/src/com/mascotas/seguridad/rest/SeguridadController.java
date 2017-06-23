package com.mascotas.seguridad.rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mascotas.application.dtos.FormError;
import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.seguridad.SeguridadService;
import com.mascotas.seguridad.UsuariosService;
import com.mascotas.seguridad.dto.LoginRequestDTO;
import com.mascotas.seguridad.dto.SessionInfoDTO;

/**
 * Servicio de Acceso a los servicios de seguridad.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
@Path("/seguridad")
public class SeguridadController {
	@EJB
	SeguridadService seguridadService;
	@EJB
	UsuariosService usuarioService;

	/**
	 * Realiza el login del usuario al sistema
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response login(@Context HttpServletRequest httpRequest, LoginRequestDTO loginRequest) throws NamingException {
		try {
			SessionInfoDTO sessionInfo = new SessionInfoDTO();
			sessionInfo.setJSessionId(httpRequest.getSession().getId());

			if (httpRequest.getUserPrincipal() != null) {
				sessionInfo.setUsuario(seguridadService.login(httpRequest.getUserPrincipal().getName()));
			} else {
				sessionInfo.setUsuario(seguridadService.login(loginRequest.getLogin()));
			}
			System.out.println("SessionId : " + sessionInfo.getJSessionId());

			httpRequest.login(loginRequest.getLogin(), loginRequest.getPassword());

			return Response.ok().entity(sessionInfo.getUsuario()).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}

	/**
	 * Realiza el login del usuario al sistema
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/logout")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response logout(@Context HttpServletRequest httpRequest) throws NamingException {
		try {
			httpRequest.logout();
			return Response.ok().build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}

	/**
	 * Realiza el login del usuario al sistema
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/principal")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response principal(@Context HttpServletRequest httpRequest) throws NamingException {
		try {
			if (httpRequest.getUserPrincipal() != null) {
				return Response.ok().entity(usuarioService.findByLogin(httpRequest.getUserPrincipal().getName())).build();
			} else {
				return Response.status(401).entity("Usuario no logueado").build();
			}
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage() != null ? e.getMessage() : "{}").build();
		}
	}
}