package com.mascotas.perfil.rest;

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
import com.mascotas.perfil.PerfilService;
import com.mascotas.perfil.dto.ActualizarPerfilDTO;

/**
 * Servicio REST de Acceso al perfil del usuario.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
@Path("/perfil")
public class PerfilController {
	@EJB
	PerfilService perfilService;

	/**
	 * Busca el perfil del usuario logueado.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCurrent(@Context HttpServletRequest httpRequest) throws NamingException {
		try {
			return Response.ok().entity(perfilService.findForLogin(httpRequest.getUserPrincipal().getName())).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}

	/**
	 * Actualiza los datos del perfil del usuario logueado.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizarPerfil(@Context HttpServletRequest httpRequest, ActualizarPerfilDTO perfilActualizado) throws NamingException {
		try {
			perfilService.actualizarPerfil(httpRequest.getUserPrincipal().getName(), perfilActualizado);
			return Response.ok().entity(perfilService.findForLogin(httpRequest.getUserPrincipal().getName())).build();
		} catch (BusinessException e) {
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
}