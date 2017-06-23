package com.mascotas.voto.rest;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mascotas.application.dtos.FormError;
import com.mascotas.application.exceptions.BusinessException;
import com.mascotas.voto.VotoService;
import com.mascotas.voto.dto.ActualizarVotoDTO;


@Stateless
@LocalBean
@Path("/voto")
public class VotoController {
	@EJB
	VotoService votoService;
	


	//Busca todos los votos
	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() throws NamingException {
		try {
			return Response.ok().entity(votoService.findAll()).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
	
	
	
	
	


	//Busca votos de usuario logueado
	@Path("/voto")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCurrent(@Context HttpServletRequest httpRequest) throws NamingException {
		try {
			return Response.ok().entity(votoService.findForLogin(httpRequest.getUserPrincipal().getName())).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}



	//Actualiza votos
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizarVoto(@Context HttpServletRequest httpRequest, ActualizarVotoDTO votoActualizado) throws NamingException {
		try {
						
			votoService.actualizarVoto(httpRequest.getUserPrincipal().getName(), votoActualizado);
			return Response.ok().entity(votoService.findForLogin(httpRequest.getUserPrincipal().getName())).build();
		} catch (BusinessException e) {
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
	
	//Elimina un voto
	@Path("/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizarVoto(@Context HttpServletRequest httpRequest, 
			@PathParam("id") Integer id) throws NamingException {
		try {
			votoService.eliminarVoto(httpRequest.getUserPrincipal().getName(), id);
			return Response.ok().build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
}