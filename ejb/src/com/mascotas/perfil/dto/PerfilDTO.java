package com.mascotas.perfil.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mascotas.perfil.entities.Perfil;
import com.mascotas.provincias.dto.ProvinciaDTO;

/**
 * Perfil de usuario
 * 
 * @author nestor
 *
 */
public class PerfilDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nombre;

	private String telefono;

	private String email;

	private ProvinciaDTO provincia;

	private String direccion;

	private String imagen;

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public ProvinciaDTO getProvincia() {
		return provincia;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getImagen() {
		return imagen;
	}

	/**
	 * Factorys para crear DTO a partir de Entity
	 * 
	 * @author nestor
	 *
	 */
	public static class Factory {
		public static PerfilDTO get(Perfil perfil) {
			PerfilDTO result = new PerfilDTO();
			result.id = perfil.getId();
			result.nombre = perfil.getNombre();
			result.telefono = perfil.getTelefono();
			result.email = perfil.getEmail();
			result.provincia = ProvinciaDTO.Factory.get(perfil.getProvincia());
			result.direccion = perfil.getDireccion();
			return result;
		}

		public static List<PerfilDTO> get(Collection<Perfil> perfiles) {
			ArrayList<PerfilDTO> result = new ArrayList<PerfilDTO>();
			for (Perfil p : perfiles) {
				result.add(get(p));
			}
			return result;
		}

	}

}
