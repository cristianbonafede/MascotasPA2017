package com.mascotas.mascotas.rest;

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
import com.mascotas.mascotas.MascotaService;
import com.mascotas.mascotas.dto.MascotaDTO;

/**
 * Servicio REST de Acceso a las Mascotas de un usuario.
 * 
 * @author Nestor
 * 
 */
@Stateless
@LocalBean
@Path("/mascota")
public class MascotaController {
	@EJB
	MascotaService mascotaService;

	/**
	 * Busca las mactoas del usuario logueado.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findMascotoas(@Context HttpServletRequest httpRequest) throws NamingException {
		try {
			return Response.ok().entity(
					mascotaService.findForLogin(httpRequest.getUserPrincipal().getName())).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}

	/**
	 * Busca las mactoas del usuario logueado.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findMasctoa(@Context HttpServletRequest httpRequest, @PathParam("id") Integer id) throws NamingException {
		try {
			return Response.ok().entity(mascotaService.findById(httpRequest.getUserPrincipal().getName(), id)).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}

	/**
	 * Actualiza o agrega una mascota a la base de datos.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizarMascota(@Context HttpServletRequest httpRequest, 
			@PathParam("id") Integer id, 
			MascotaDTO mascotaDTO) throws NamingException {
		try {
			int mascotaId = mascotaService.actualizarMascota(httpRequest.getUserPrincipal().getName(), mascotaDTO);
			return Response.ok().entity(mascotaService.findById(httpRequest.getUserPrincipal().getName(), mascotaId)).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
	
	/**
	 * Actualiza o agrega una mascota a la base de datos.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response actualizarMascota(@Context HttpServletRequest httpRequest, 
			@PathParam("id") Integer id) throws NamingException {
		try {
			mascotaService.eliminarMascota(httpRequest.getUserPrincipal().getName(), id);
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
	 * Graba una mascota a la base de datos.
	 * 
	 * @return
	 * @throws NamingException
	 */
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response agregarMascota(@Context HttpServletRequest httpRequest, MascotaDTO mascotaDTO) throws NamingException {
		try {
			int id = mascotaService.actualizarMascota(httpRequest.getUserPrincipal().getName(), mascotaDTO);
			return Response.ok().entity(mascotaService.findById(httpRequest.getUserPrincipal().getName(), id)).build();
		} catch (BusinessException e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity(FormError.processError(e)).build();
		}
	}
}