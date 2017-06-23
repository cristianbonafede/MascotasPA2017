package com.mascotas.concurso.rest;

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
import com.mascotas.concurso.ConcursoService;
import com.mascotas.concurso.dto.ActualizarConcursoDTO;

//Servicio RET

@Stateless
@LocalBean
@Path("/concurso")
public class ConcursoController {
	@EJB
	ConcursoService concursoService;
	


	//Busca todos los concursos
	@Path("/all")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() throws NamingException {
		try {
			return Response.ok().entity(concursoService.findAll()).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
	
	
	
	
	

	//Busca Concurso de usuario logueado
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCurrent(@Context HttpServletRequest httpRequest) throws NamingException {
		try {
			return Response.ok().entity(concursoService.findForLogin(httpRequest.getUserPrincipal().getName())).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}

	
	//Actualiza la informacion del concurso
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizarConcurso(@Context HttpServletRequest httpRequest, ActualizarConcursoDTO concursoActualizado) throws NamingException {
		try {
			concursoService.actualizarConcurso(httpRequest.getUserPrincipal().getName(), concursoActualizado);
			return Response.ok().entity(concursoService.findForLogin(httpRequest.getUserPrincipal().getName())).build();
		} catch (BusinessException e) {
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
}