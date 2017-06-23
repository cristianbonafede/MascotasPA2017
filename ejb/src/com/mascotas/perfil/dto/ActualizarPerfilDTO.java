package com.mascotas.perfil.dto;

import com.mascotas.perfil.entities.Perfil;

public class ActualizarPerfilDTO {
	private Integer id;
	private String nombre;
	private String telefono;
	private String email;
	private String direccion;
	private Integer provincia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getProvincia() {
		return provincia;
	}

	public void setProvincia(Integer provincia) {
		this.provincia = provincia;
	}

	/**
	 * Factorys para crear DTO a partir de Entity
	 * 
	 * @author nestor
	 *
	 */
	public static class Factory {
		public static ActualizarPerfilDTO get(Perfil perfil) {
			ActualizarPerfilDTO result = new ActualizarPerfilDTO();
			result.id = perfil.getId();
			result.nombre = perfil.getNombre();
			result.telefono = perfil.getTelefono();
			result.email = perfil.getEmail();
			result.direccion = perfil.getDireccion();
			if (perfil.getProvincia() != null) {
				result.provincia = perfil.getProvincia().getId();
			}
			return result;
		}
	}
}
