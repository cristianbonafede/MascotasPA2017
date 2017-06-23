package com.mascotas.application.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.mascotas.application.rest.CORSFilter;
import com.mascotas.concurso.rest.ConcursoController;
import com.mascotas.mascotas.rest.MascotaController;
import com.mascotas.perfil.rest.PerfilController;
import com.mascotas.provincias.rest.ProvinciaController;
import com.mascotas.seguridad.rest.SeguridadController;
import com.mascotas.seguridad.rest.UsuariosControler;
import com.mascotas.voto.rest.VotoController;
/**
 * Configura los servicios rest en el contenedor WEB.
 * 
 * @author Nestor
 *
 */
@ApplicationPath("/rest")
public class ApplicationConfig extends Application {
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList(CORSFilter.class, UsuariosControler.class, SeguridadController.class,
				ProvinciaController.class, PerfilController.class, MascotaController.class, ConcursoController.class, VotoController.class));
	}
}